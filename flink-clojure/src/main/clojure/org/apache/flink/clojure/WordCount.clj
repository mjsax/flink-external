;
; Licensed to the Apache Software Foundation (ASF) under one
; or more contributor license agreements.  See the NOTICE file
; distributed with this work for additional information
; regarding copyright ownership.  The ASF licenses this file
; to you under the Apache License, Version 2.0 (the
; "License"); you may not use this file except in compliance
; with the License.  You may obtain a copy of the License at
;
;     http://www.apache.org/licenses/LICENSE-2.0
;
; Unless required by applicable law or agreed to in writing, software
; distributed under the License is distributed on an "AS IS" BASIS,
; WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
; See the License for the specific language governing permissions and
; limitations under the License.
;

(ns org.apache.flink.clojure.WordCount
  (:import
  (org.apache.flink.api.common.functions FlatMapFunction)
  (org.apache.flink.api.java ExecutionEnvironment)
  (org.apache.flink.api.java.tuple Tuple2))
  (:require [clojure.string :as str])
  (:gen-class))

(def flink-env (ExecutionEnvironment/getExecutionEnvironment))

(def text (.fromElements flink-env (to-array ["please test me and me too"])))

(deftype tokenizer [] FlatMapFunction
                     (flatMap [this value collector]
                       (doseq [v (str/split value #"\s")]
                           (.collect collector (Tuple2. v (int 1))))))

(def tokens (.returns (.flatMap text (tokenizer.)) "Tuple2<String,Integer>"))

(def counts (.sum (.groupBy tokens (int-array [0])) 1))

(defn -main []
  (.print counts)
)

;; enable to execute within Intellij
;;(-main)