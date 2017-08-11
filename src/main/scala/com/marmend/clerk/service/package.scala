package com.marmend.clerk

import com.marmend.clerk.model._
import java.net.URL

import com.fasterxml.jackson.databind.{ObjectMapper, PropertyNamingStrategy}
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.fasterxml.jackson.module.scala.experimental.ScalaObjectMapper

/**
 * Created by maksim on 8/10/17.
 */
package object service {

  object Json {
    def mapper = {
      val m = new ObjectMapper() with ScalaObjectMapper
      m.registerModule(DefaultScalaModule)
      m
    }

    def fromJson[T](url: URL)(implicit m: Manifest[T]): T = {
      mapper.readValue[T](url)
    }

    def fromJson[T](value: String, allowUnderscores: Boolean = false)(implicit m: Manifest[T]): T = {
      val mapper = if (allowUnderscores) {
        val m = this.mapper
        m.setPropertyNamingStrategy(
          PropertyNamingStrategy.SNAKE_CASE
        )
        m
      } else this.mapper
      mapper.readValue[T](value)
    }
  }

  lazy val loadApp = Json.fromJson[GoogleApp](this.getClass.getClassLoader().getResource("google_secrets.json"))

  val authUrlBase = "https://accounts.google.com/o/oauth2/auth"
  val tokenUrlBase = "https://www.googleapis.com/oauth2/v3/token"
}
