(ns monster.core
  (:require [timewords.core :as timewords]
            [common-crawl-utils.utils :as cc-utils]
            [common-crawl-utils.coordinates :as cc-coordinates]
            [common-crawl-utils.fetcher :as cc-fetcher]
            [clojure.string :as str]
            [beagle.phrases :as phrases]
            [clojure.tools.logging :as log])
  (:import (java.util Date)
           (com.chimbori.crux.articles Article ArticleExtractor)))

(def dictionary
  [{:text "Karbauskis"}])

(defn filter-cdx-api-urls [^String start ^String end]
  ;; get the latest json and filter entries that fall into the time period
  (let [^Date start-timestamp (timewords/parse start)
        ^Date end-timestamp (timewords/parse end)
        all-crawls (cc-utils/get-crawls)]
    (map :cdx-api
         (filter #(let [crawl-date (timewords/parse (str/replace (:name %) " Index" ""))]
                    (and (.before ^Date start-timestamp crawl-date)
                         (.after ^Date end-timestamp crawl-date))) all-crawls))))

(defn fetch-coordinates [^String host ^String cdx-api-url]
  (take 100 (shuffle (take 10000 (cc-coordinates/fetch {:url       host
                                                        :matchType "host"
                                                        :cdx-api   cdx-api-url})))))

(defn ccu-fetch-content [coordinate]
  (cc-fetcher/fetch-single-coordinate-content coordinate))

(defn parse-with-crux [^String url ^String html]
  (let [^Article article (try
                           (-> (ArticleExtractor/with url html)
                               (.extractMetadata)
                               (.extractContent)
                               (.article))
                           (catch Exception e
                             (-> (ArticleExtractor/with url "<html><body><h1>This is an article</h1></body></html>")
                                 (.extractContent)
                                 (.article))))
        txt (.text (.-document article))]
    {:url          url
     :publish-date nil
     :title        (.-title article)
     :description  (.-description article)
     :body         txt}))

(defn extract-article [content]
  (parse-with-crux (:url content) (-> content :content :html)))

(defn highlight [coordinate highlighter-fn]
  (let [content (ccu-fetch-content coordinate)
        {:keys [url title description body] :as article} (extract-article content)]
    (assoc article :highlights (concat (highlighter-fn title)
                                       (highlighter-fn description)
                                       (highlighter-fn body)))))

(defn match [host cdx-api-url highlighter-fn]
  (let [coordinates (fetch-coordinates host cdx-api-url)]
    (map #(highlight % highlighter-fn) coordinates)))

; E.g. (monster.core/search "delfi.lt" "2019-06-01" "2019-10-01" monster.dict/mps)
(defn search [host start end dictionary]
  (let [highlighter-fn (phrases/highlighter dictionary {:case-sensitive? false
                                                        :ascii-fold? true
                                                        :stem? true
                                                        :stemmer :lithuanian})
        cdx-api-urls (filter-cdx-api-urls start end)]
    (map #(match host % highlighter-fn) cdx-api-urls)))

; Get Url with annotations
; (clojure.pprint/pprint
;  (remove (fn [article] (empty? (:highlights article)))
;          (mapcat #(map (fn [article] (select-keys article [:url :highlights])) %)
;                  (monster.core/search "delfi.lt" "2019-06-01" "2019-10-01" monster.dict/mps))))
