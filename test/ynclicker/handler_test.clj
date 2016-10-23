(ns ynclicker.handler-test
  (:require [cheshire.core :as cheshire]
            [midje.sweet :refer :all]
            [ynclicker.handler :refer :all]
            [ring.mock.request :as mock]))

(defn parse-body [body]
  (cheshire/parse-string (slurp body) true))

(facts "Compojure api tests"

  (fact "Test GET request to /votes? returns expected response"
        (let [vote {:token "foo"
                    :response :yes
                    :eventid "id1"}
              request (app (-> (mock/request :post "/api/votes")
                               (mock/content-type "application/json")
                               (mock/body  (cheshire/generate-string vote))))
              response (app (-> (mock/request :get "/api/votes/id1")))
              body     (parse-body (:body response))]
      (:status response) => 200
      (:result body)    => [{:token "foo"
                             :response "yes"
                             :eventid "id1"}])))
