package com.marmend.clerk.model

/**
 * Created by maksim on 8/10/17.
 */

case class DriveResource(link: String, title: String, thumbnail: String)

case class User(email: String)

case class GoogleApp(
                      clientId: String,
                      clientSecret: String,
                      redirectUrl: String,
                      scopes: List[String]
                      )

case class GoogleCredential(
                             userEmail: String,
                             accessToken: String,
                             refreshToken: String
                             )

case class RefreshTokenResponse(
                                 accessToken: String,
                                 expiresIn: Int,
                                 tokenType: String
                                 )

case class TokenResponse(
                          accessToken: String,
                          expiresIn: Int,
                          tokenType: String,
                          refreshToken: String
                          )
