package com.github.leosilvadev.tkee

import com.danielasfregola.twitter4s.entities.{AccessToken, ConsumerToken}
import com.github.leosilvadev.tkee.sink.TweetPublisher
import com.github.leosilvadev.tkee.source.{Twitter, TwitterSource}
import com.typesafe.config.ConfigFactory

object Application extends App {

  val config = ConfigFactory.load()
  val twitterConfig = config.getConfig("twitter")
  val consumerConfig = twitterConfig.getConfig("consumer")
  val accessConfig = twitterConfig.getConfig("access")
  val kafkaConfig = config.getConfig("kafka")

  val consumerToken = ConsumerToken(key = consumerConfig.getString("key"), secret = consumerConfig.getString("secret"))
  val accessToken = AccessToken(key = accessConfig.getString("key"), secret = accessConfig.getString("secret"))

  val topic = "local-tweets"

  val publisher = TweetPublisher(kafkaConfig.getString("server"), kafkaConfig.getString("tweetTopic"))

  Twitter.listen(
    topic,
    TwitterSource(consumerToken, accessToken),
    TweetPublisher.publish(publisher)("my_key", _)
  )
}