package com.github.leosilvadev.tkee.source

import com.danielasfregola.twitter4s.entities.Tweet
import com.danielasfregola.twitter4s.entities.streaming.StreamingMessage

object Twitter {

  def listen(topic: String, source: TwitterSource): Unit =
    source.client.sampleStatuses()(produceTo(topic))

  private def produceTo(topic: String): PartialFunction[StreamingMessage, Unit] = {
    case tweet: Tweet => println(tweet.text)
  }

}
