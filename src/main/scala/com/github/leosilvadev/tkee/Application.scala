package com.github.leosilvadev.tkee

import com.danielasfregola.twitter4s.entities.{AccessToken, ConsumerToken}
import com.github.leosilvadev.tkee.source.{Twitter, TwitterSource}
import com.typesafe.config.ConfigFactory

object Application extends App {

  val config = ConfigFactory.load()
  val consumerConfig = config.getObject("consumer")
  val producerConfig = config.getObject("producer")

  val consumerToken = ConsumerToken(key = consumerConfig("key"), secret = consumerConfig("secret"))
  val accessToken = AccessToken(key = producerConfig("key"), secret = producerConfig("secret"))

  val topic = "local-tweets"

  Twitter.listen(topic, TwitterSource(consumerToken, accessToken))
}
a