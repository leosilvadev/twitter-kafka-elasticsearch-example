name := "twitter-kafka-elasticsearch-example"

version := "0.1"

scalaVersion := "2.12.0"

libraryDependencies ++= Seq(
  "org.apache.kafka" % "kafka-clients" % "2.3.0",
  "com.danielasfregola" %% "twitter4s" % "6.1"
)

