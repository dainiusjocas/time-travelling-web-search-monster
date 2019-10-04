(ns monster.core
  (:require [timewords.core :as timewords]
            [common-crawl-utils.utils :as cc-utils]
            [common-crawl-utils.coordinates :as cc-coordinates]
            [common-crawl-utils.fetcher :as cc-fetcher]
            [clojure.string :as str]
            [beagle.phrases :as phrases])
  (:import (java.util Date)))

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
  (cc-coordinates/fetch {:url host
                         :matchType "host"
                         :cdx-api cdx-api-url}))

(defn ccu-fetch-content [coordinate]
  (cc-fetcher/fetch-single-coordinate-content coordinate))

(defn extract-article [content]
  {})

(defn highlight [coordinate highlighter-fn]
  (let [content (ccu-fetch-content coordinate)
        {:keys [title body] :as article} (extract-article content)]
    (assoc article :highlights (concat (highlighter-fn title)
                                       (highlighter-fn body)))))

(defn match [host cdx-api-url highlighter-fn]
  (let [coordinates (fetch-coordinates host cdx-api-url)]
    (map #(highlight % highlighter-fn) coordinates)))

(defn search [host start end dictionary]
  (let [highlighter-fn (phrases/highlighter dictionary)
        cdx-api-urls (filter-cdx-api-urls start end)]
    (map #(match host % highlighter-fn) cdx-api-urls)))