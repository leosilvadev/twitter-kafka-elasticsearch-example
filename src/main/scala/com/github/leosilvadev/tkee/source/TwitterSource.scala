package com.github.leosilvadev.tkee.source

import com.danielasfregola.twitter4s.TwitterStreamingClient
import com.danielasfregola.twitter4s.entities.{AccessToken, ConsumerToken}

class TwitterSource(val client: TwitterStreamingClient)

object TwitterSource {

  def apply(token: ConsumerToken, accessToken: AccessToken): TwitterSource =
    new TwitterSource(TwitterStreamingClient(token, accessToken))

}
