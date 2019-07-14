package com.github.leosilvadev.tkee.sink

import java.util.Properties

import com.danielasfregola.twitter4s.entities.Tweet
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerConfig, ProducerRecord}

import scala.util.Try

class TweetPublisher(val producer: KafkaProducer[String, String], val topic: String)

object TweetPublisher {

  def apply(server: String, topic: String): TweetPublisher = {
    val props = new Properties()
    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, server)
    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer")
    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer")
    new TweetPublisher(new KafkaProducer[String, String](props), topic)
  }

  def publish(publisher: TweetPublisher)(key: String, status: Tweet): Try[Unit] = Try {
    val record = new ProducerRecord[String, String](publisher.topic, "key", "value")
    publisher.producer.send(record)
    publisher.producer.flush()
  }

}