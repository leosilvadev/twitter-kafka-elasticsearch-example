package com.github.leosilvadev.tkee.source

import com.danielasfregola.twitter4s.entities.Tweet
import com.danielasfregola.twitter4s.entities.streaming.StreamingMessage

object Twitter {

  def listen(topic: String, source: TwitterSource, onTweet: Tweet => Unit): Unit =
    source.client.sampleStatuses()(handle(onTweet))

  private def handle(onTweet: Tweet => Unit): PartialFunction[StreamingMessage, Unit] = {
    case tweet: Tweet => onTweet(tweet)
  }

}
