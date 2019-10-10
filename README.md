# time-traveling-web-search-monster

A semi real-world example on how the (Beagle)[https://github.com/tokenmill/beagle] library can be used.

Intended use is for searching in the news websites, such as `https://www.bbc.com/` because the content extraction is optimized for exactly such content.

Example query:
```clojure
(->> (monster.core/search "delfi.lt" "2019-06-01" "2019-10-01" monster.dict/mps)
     (mapcat #(map (fn [article] (select-keys article [:url :highlights])) %))
     (remove (fn [article] (empty? (:highlights article))))
     (clojure.pprint/pprint))
```

This would result in similar list of annotations:
```clojure
[{:url
  "https://www.delfi.lt/m360/eksperto-zvilgsnis/skaitmenines-diplomatijos-ekspertas-kaip-socialiniai-tinklai-pakeite-pasaulio-lyderiu-komunikacija.d?id=81559725",
  :highlights
  ({:text "Ingrida Šimonytė",
    :type "PHRASE",
    :dict-entry-id "122",
    :meta {},
    :begin-offset 3866,
    :end-offset 3882})}
 {:url
  "https://www.delfi.lt/news/daily/lithuania/rusijos-itakos-voratinklis-itakingu-politiku-rankose-kur-klaidziojo-nutyleta-informacija-apie-seimo-nare.d?id=82199857",
  :highlights
  ({:text "Irinos Rozovos",
    :type "PHRASE",
    :dict-entry-id "97",
    :meta {},
    :begin-offset 126,
    :end-offset 140}
   {:text "Viktoras Pranckietis",
    :type "PHRASE",
    :dict-entry-id "88",
    :meta {},
    :begin-offset 4203,
    :end-offset 4223}
   {:text "Irena Degutienė",
    :type "PHRASE",
    :dict-entry-id "24",
    :meta {},
    :begin-offset 2966,
    :end-offset 2981}
   {:text "Virgilijus Alekna",
    :type "PHRASE",
    :dict-entry-id "2",
    :meta {},
    :begin-offset 8819,
    :end-offset 8836}
   {:text "Ramūnas Karbauskis",
    :type "PHRASE",
    :dict-entry-id "47",
    :meta {},
    :begin-offset 5784,
    :end-offset 5802}
   {:text "Ramūnas Karbauskis",
    :type "PHRASE",
    :dict-entry-id "47",
    :meta {},
    :begin-offset 6091,
    :end-offset 6109}
   {:text "Gabrielius Landsbergis",
    :type "PHRASE",
    :dict-entry-id "59",
    :meta {},
    :begin-offset 5454,
    :end-offset 5476}
   {:text "Gabrielius Landsbergis",
    :type "PHRASE",
    :dict-entry-id "59",
    :meta {},
    :begin-offset 8882,
    :end-offset 8904}
   {:text "Laurynas Kasčiūnas",
    :type "PHRASE",
    :dict-entry-id "48",
    :meta {},
    :begin-offset 8799,
    :end-offset 8817}
   {:text "Juozui Bernatoniui",
    :type "PHRASE",
    :dict-entry-id "15",
    :meta {},
    :begin-offset 1166,
    :end-offset 1184}
   {:text "Arvydas Anušauskas",
    :type "PHRASE",
    :dict-entry-id "4",
    :meta {},
    :begin-offset 8916,
    :end-offset 8934}
   {:text "Dainius Gaižauskas",
    :type "PHRASE",
    :dict-entry-id "29",
    :meta {},
    :begin-offset 6400,
    :end-offset 6418}
   {:text "Sauliui Skverneliui",
    :type "PHRASE",
    :dict-entry-id "107",
    :meta {},
    :begin-offset 984,
    :end-offset 1003}
   {:text "Vytautui Bakui",
    :type "PHRASE",
    :dict-entry-id "9",
    :meta {},
    :begin-offset 1106,
    :end-offset 1120})}
 {:url
  "https://www.delfi.lt/news/daily/lithuania/patarejas-su-veryga-aptarti-pokyciai-sistemoje-ne-ministro-likimo-poste-klausimas.d?id=81875607",
  :highlights
  ({:text "Aurelijumi Veryga",
    :type "PHRASE",
    :dict-entry-id "135",
    :meta {},
    :begin-offset 67,
    :end-offset 84}
   {:text "Aurelijumi Veryga",
    :type "PHRASE",
    :dict-entry-id "135",
    :meta {},
    :begin-offset 59,
    :end-offset 76}
   {:text "Saulius Skvernelis",
    :type "PHRASE",
    :dict-entry-id "107",
    :meta {},
    :begin-offset 1774,
    :end-offset 1792})}
 {:url
  "https://www.delfi.lt/archive/nvenckiene-koaliciju-neisivaizduoja-su-ts-lkd-socdemais-liberalais-ir-dp-vmazuronis-su-dabartiniais-valdanciaisiais-balsavimas.d?id=59580999",
  :highlights
  ({:text "Irena Degutienė",
    :type "PHRASE",
    :dict-entry-id "24",
    :meta {},
    :begin-offset 1622,
    :end-offset 1637}
   {:text "Andrius Kubilius",
    :type "PHRASE",
    :dict-entry-id "58",
    :meta {},
    :begin-offset 3293,
    :end-offset 3309})}
 {:url
  "https://www.delfi.lt/news/daily/lithuania/itakingiausiuju-sarasas-kodel-visuomene-pasirinko-nauseda-o-elitas-grybauskaite.d?id=82021089",
  :highlights
  ({:text "Ramūno Karbauskio",
    :type "PHRASE",
    :dict-entry-id "47",
    :meta {},
    :begin-offset 1930,
    :end-offset 1947}
   {:text "Juozas Olekas",
    :type "PHRASE",
    :dict-entry-id "81",
    :meta {},
    :begin-offset 721,
    :end-offset 734}
   {:text "Algirdas Butkevičius",
    :type "PHRASE",
    :dict-entry-id "20",
    :meta {},
    :begin-offset 752,
    :end-offset 772})}
 {:url
  "https://www.delfi.lt/news/daily/lithuania/pranckietis-as-nesuteiksiu-tokios-garbes-ismesti-mane-is-partijos.d?id=81803669",
  :highlights
  ({:text "Viktoras Pranckietis",
    :type "PHRASE",
    :dict-entry-id "88",
    :meta {},
    :begin-offset 26,
    :end-offset 46}
   {:text "Ramūnas Karbauskis",
    :type "PHRASE",
    :dict-entry-id "47",
    :meta {},
    :begin-offset 1037,
    :end-offset 1055})}
 {:url
  "https://www.delfi.lt/news/daily/lithuania/landsbergis-apie-eurokomisaro-posta-neturesime-nei-stipraus-zmogaus-nei-reiksmingos-figuros.d?id=81817413",
  :highlights
  ({:text "Virginijus Sinkevičius",
    :type "PHRASE",
    :dict-entry-id "102",
    :meta {},
    :begin-offset 233,
    :end-offset 255}
   {:text "Gabrielius Landsbergis",
    :type "PHRASE",
    :dict-entry-id "59",
    :meta {},
    :begin-offset 79,
    :end-offset 101}
   {:text "Virginijų Sinkevičių",
    :type "PHRASE",
    :dict-entry-id "102",
    :meta {},
    :begin-offset 1268,
    :end-offset 1288}
   {:text "Saulius Skvernelis",
    :type "PHRASE",
    :dict-entry-id "107",
    :meta {},
    :begin-offset 1421,
    :end-offset 1439})}
 {:url
  "https://www.delfi.lt/news/ringas/lit/raimundas-lopata-eurokomisaro-skyrimas-kaip-lietuvos-pazeminimas.d?id=81846743",
  :highlights
  ({:text "Ingrida Šimonytė",
    :type "PHRASE",
    :dict-entry-id "122",
    :meta {},
    :begin-offset 2932,
    :end-offset 2948})}
 {:url
  "https://www.delfi.lt/news/daily/world/kuo-panasus-kuo-skiriasi-rinkimai-gruzijoje-ir-lietuvoje.d?id=72501112",
  :highlights
  ({:text "Ramūno Karbauskio",
    :type "PHRASE",
    :dict-entry-id "47",
    :meta {},
    :begin-offset 4450,
    :end-offset 4467})}
 {:url
  "https://www.delfi.lt/news/daily/lithuania/streikuojantys-mokytojai-ne-vienintele-petrauskienes-beda.d?id=79776627",
  :highlights
  ({:text "Sauliui Skverneliui",
    :type "PHRASE",
    :dict-entry-id "107",
    :meta {},
    :begin-offset 89,
    :end-offset 108})}
 {:url
  "https://www.delfi.lt/news/daily/lithuania/nauseda-turi-konkreciu-pasiulymu-del-moteru-i-ministru-postus.d?id=81742415",
  :highlights
  ({:text "Sauliumi Skverneliu",
    :type "PHRASE",
    :dict-entry-id "107",
    :meta {},
    :begin-offset 44,
    :end-offset 63}
   {:text "Saulius Skvernelis",
    :type "PHRASE",
    :dict-entry-id "107",
    :meta {},
    :begin-offset 1029,
    :end-offset 1047})}
 {:url
  "https://www.delfi.lt/news/daily/lithuania/konservatoriu-etikos-sargai-pritaike-sankcijas-dagiui-viena-is-ju-ypac-skaudi.d?id=81549191",
  :highlights
  ({:text "Rimanto Jono Dagio",
    :type "PHRASE",
    :dict-entry-id "23",
    :meta {},
    :begin-offset 118,
    :end-offset 136}
   {:text "Ingridos Šimonytės",
    :type "PHRASE",
    :dict-entry-id "122",
    :meta {},
    :begin-offset 1419,
    :end-offset 1437})}
 {:url "https://www.delfi.lt/spausdinti/?id=81448589",
  :highlights
  ({:text "Saulius Skvernelis",
    :type "PHRASE",
    :dict-entry-id "107",
    :meta {},
    :begin-offset 4527,
    :end-offset 4545})}
 {:url
  "https://www.delfi.lt/news/daily/lithuania/tamasuniene-apie-sirinskiene-reikia-spresti-kaip-tai-dera-su-laisvu-seimo-nario-mandatu.d?id=81695661",
  :highlights
  ({:text "Agnės Širinskienės",
    :type "PHRASE",
    :dict-entry-id "123",
    :meta {},
    :begin-offset 213,
    :end-offset 231}
   {:text "Rita Tamašunienė",
    :type "PHRASE",
    :dict-entry-id "125",
    :meta {},
    :begin-offset 55,
    :end-offset 71}
   {:text "Ramūnas Karbauskis",
    :type "PHRASE",
    :dict-entry-id "47",
    :meta {},
    :begin-offset 650,
    :end-offset 668})}
 {:url
  "https://www.delfi.lt/news/daily/crime/motina-vaikus-iskeite-i-sugyventini-priekabiavusi-prie-jos-mazyliu.d?id=23747719",
  :highlights
  ({:text "Rimantės Šalaševičiūtės",
    :type "PHRASE",
    :dict-entry-id "117",
    :meta {},
    :begin-offset 290,
    :end-offset 313})}
 {:url
  "https://www.delfi.lt/news/daily/lithuania/konservatoriai-prieme-galutini-sprendima-dagys-su-jais-kituose-seimo-rinkimuose-dalyvauti-negales.d?id=81561613",
  :highlights
  ({:text "Rimantui Jonui Dagiui",
    :type "PHRASE",
    :dict-entry-id "23",
    :meta {},
    :begin-offset 123,
    :end-offset 144}
   {:text "Ingridos Šimonytės",
    :type "PHRASE",
    :dict-entry-id "122",
    :meta {},
    :begin-offset 4679,
    :end-offset 4697}
   {:text "Gabrieliui Landsbergiui",
    :type "PHRASE",
    :dict-entry-id "59",
    :meta {},
    :begin-offset 1155,
    :end-offset 1178}
   {:text "Gabrielius Landsbergis",
    :type "PHRASE",
    :dict-entry-id "59",
    :meta {},
    :begin-offset 1481,
    :end-offset 1503}
   {:text "Gabrielius Landsbergis",
    :type "PHRASE",
    :dict-entry-id "59",
    :meta {},
    :begin-offset 1554,
    :end-offset 1576}
   {:text "Gabrieliui Landsbergiui",
    :type "PHRASE",
    :dict-entry-id "59",
    :meta {},
    :begin-offset 5333,
    :end-offset 5356}
   {:text "Laurynas Kasčiūnas",
    :type "PHRASE",
    :dict-entry-id "48",
    :meta {},
    :begin-offset 2783,
    :end-offset 2801})}
 {:url
  "https://www.delfi.lt/news/daily/lithuania/veryga-nusitaike-i-medicinos-biblioteka-uzsiunde-audita-ir-panoro-likviduoti.d?id=80123675",
  :highlights
  ({:text "Aurelijui Verygai",
    :type "PHRASE",
    :dict-entry-id "135",
    :meta {},
    :begin-offset 52,
    :end-offset 69}
   {:text "Juozas Olekas",
    :type "PHRASE",
    :dict-entry-id "81",
    :meta {},
    :begin-offset 346,
    :end-offset 359})}
 {:url
  "https://www.delfi.lt/news/daily/lithuania/gali-zlugti-r-zilinsko-karjera-teismas-ji-pripazino-kaltu.d?id=41557421",
  :highlights
  ({:text "Rokui Žilinskui",
    :type "PHRASE",
    :dict-entry-id "140",
    :meta {},
    :begin-offset 21,
    :end-offset 36}
   {:text "Stasys Šedbaras",
    :type "PHRASE",
    :dict-entry-id "119",
    :meta {},
    :begin-offset 2021,
    :end-offset 2036})}
 {:url
  "https://www.delfi.lt/news/daily/lithuania/po-frakcijos-griuties-zemaitaitis-svarsto-ne-viena-scenariju.d?id=81545889&utm_source=rss&utm_medium=rss&utm_campaign=rss",
  :highlights
  ({:text "Remigijus Žemaitaitis",
    :type "PHRASE",
    :dict-entry-id "139",
    :meta {},
    :begin-offset 48,
    :end-offset 69}
   {:text "Ramūnas Karbauskis",
    :type "PHRASE",
    :dict-entry-id "47",
    :meta {},
    :begin-offset 1650,
    :end-offset 1668}
   {:text "Valentinui Bukauskui",
    :type "PHRASE",
    :dict-entry-id "18",
    :meta {},
    :begin-offset 376,
    :end-offset 396})}
 {:url "https://www.delfi.lt/spausdinti/?id=75212524",
  :highlights
  ({:text "Virginijus Sinkevičius",
    :type "PHRASE",
    :dict-entry-id "102",
    :meta {},
    :begin-offset 1350,
    :end-offset 1372}
   {:text "Gabrielius Landsbergis",
    :type "PHRASE",
    :dict-entry-id "59",
    :meta {},
    :begin-offset 3278,
    :end-offset 3300}
   {:text "Dovilė Šakalienė",
    :type "PHRASE",
    :dict-entry-id "116",
    :meta {},
    :begin-offset 1082,
    :end-offset 1098}
   {:text "Andrius Palionis",
    :type "PHRASE",
    :dict-entry-id "83",
    :meta {},
    :begin-offset 2695,
    :end-offset 2711}
   {:text "Greta Kildišienė",
    :type "PHRASE",
    :dict-entry-id "51",
    :meta {},
    :begin-offset 875,
    :end-offset 891}
   {:text "Eugenijus Gentvilas",
    :type "PHRASE",
    :dict-entry-id "31",
    :meta {},
    :begin-offset 4448,
    :end-offset 4467})}
 {:url "https://www.delfi.lt/spausdinti/?id=73234666",
  :highlights
  ({:text "Saulius SKvernelis",
    :type "PHRASE",
    :dict-entry-id "107",
    :meta {},
    :begin-offset 102,
    :end-offset 120})}
 {:url
  "https://www.delfi.lt/news/daily/lithuania/generalinis-prokuroras-susitiko-su-nauseda-pagrindinis-klausimas-politiku-itaka-ir-skaidrumas.d?id=81496403",
  :highlights
  ({:text "Agnės Širinskienės",
    :type "PHRASE",
    :dict-entry-id "123",
    :meta {},
    :begin-offset 1628,
    :end-offset 1646})}]
```

If you need the text snippets, then use `subs` with the `[:begin-offset :end-offset]` from `:body`.
