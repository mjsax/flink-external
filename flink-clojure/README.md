Small WordCount example on how to write a Flink program in Clojure.

Steps to run this code with `lein`:

1. run `lein compile org.apache.flink.clojure.WordCount`
2. execute with `lein run`

Steps to run this code with `bin/flink`:

1. run `mvn -DskipTests clean package`
2. execute with `bin/flink run <path-to>/flink-clojure/target/WordCount-Clojure.jar`

*(This is broken in Flink-0.10.1 but got fixed in current Flink master branch: https://github.com/apache/flink/commit/aec6ded5ec7caeaf0dca798be216a3fd9e6cd849)*

You can also run this example within Intellij IDEA (with Cursive plugin).
