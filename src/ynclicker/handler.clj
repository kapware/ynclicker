(ns ynclicker.handler
  (:require [compojure.api.sweet :refer :all]
            [ring.util.http-response :refer :all]
            [schema.core :as s]
            [alandipert.enduro :as e]))

(s/defschema Vote
  {:token s/Str
   :response (s/enum :yes :no)
   :eventid s/Str})

(def votes-dir (System/getProperty "votes.dir" (System/getProperty "user.dir")))

(def votes (e/file-atom {} (str votes-dir "/votes.clj") :pending-dir votes-dir))

(defn vote! [vote]
  (do
    (let [eventid (:eventid vote)
          token (:token vote)]
      (e/swap! votes assoc-in [eventid token] vote))
    vote))

(def app
  (api
    {:swagger
     {:ui "/"
      :spec "/swagger.json"
      :data {:info {:title "Ynclicker"
                    :description "Compojure Api example"}
             :tags [{:name "api", :description "some apis"}]}}}

    (context "/api" []
      :tags ["api"]

      (GET "/votes/:eventid" [eventid]
        :return {:result [Vote]}
        :summary "Return placed votes"
        (ok {:result (vals (get @votes eventid))}))

      (POST "/votes" []
        :return Vote
        :body [vote Vote]
        :summary "Places a single vote from a person"
        (ok (vote! vote))))))
