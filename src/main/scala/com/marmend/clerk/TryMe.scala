package com.marmend.clerk

import com.marmend.clerk.model.User
import com.marmend.clerk.service.{GoogleSheetService, GoogleDriveService, AuthService}
import org.http4s._
import org.http4s.headers._
import org.http4s.MediaType._
import org.http4s.dsl._

object TryMe {

  object CodeQueryParamMatcher extends QueryParamDecoderMatcher[String]("code")

  val authService = new AuthService()
  val driveService = new GoogleDriveService()
  val sheetService = new GoogleSheetService()
  val service = HttpService {

    case GET -> Root / "hello" / email =>
      Ok(s"<p>Please click on link and grant access: <a href=${authService.userUri(User(1, email))}>${email}</a></p>")
        .withContentType(Some(`Content-Type`(`text/html`)))

    case GET -> Root / "hello" / email / access :? CodeQueryParamMatcher(code) =>
      val gCredentials = authService.buildCredential(code, User(1, email))
      val drive = driveService.getDriveForToken(gCredentials.accessToken)
      val sheet = sheetService.getSheetForToken(gCredentials.accessToken)
      Ok(
        s"""This is your access token: ${gCredentials.accessToken}
           |This is your refresh token: ${gCredentials.refreshToken}
           |This is your Drive: ${drive.files().list().size()}
           |This is your Sheet version: ${sheet.getServiceVersion}
         """.stripMargin)
  }
}

