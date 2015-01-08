(ns foxglove.core
  (:require [clj-http.client :as http]
            [clojure.tools.logging :as log]))

(defn message!
  "POST /notify-api/message

   Sends MESSAGE to a Walker endpoint located at URL. Accepts SERVICES and
   OPTIONS parameters which do as explained by the Walker documentation.

   It is strongly recommended you partially apply this function, and the
   parameters are ordered with this specifically in mind."
  [url services sender options message & {:keys [exceptions?]
                                          :or {exceptions? false}}]
  (let [payload {:message  message
                 :sender   sender
                 :options  options
                 :services services}
        full-url (str url "/notify-api/message")]
    (http/post full-url {:form-params payload
                         :content-type :json
                         :coerce :always
                         :as :json
                         :throw-exceptions exceptions?})))

(defn services
  "GET /notify-api/services

   Retrieves the list of services Walker implements."
  [url & {:keys [exceptions?]
          :or {exceptions? false}}]
  (let [full-url (str url "/notify-api/services")]
    (http/get full-url {:coerce :always
                        :as :json
                        :throw-exceptions exceptions?})))
