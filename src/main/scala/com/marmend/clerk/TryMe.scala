package com.marmend.clerk

import com.marmend.clerk.model.{GoogleCredential, User}
import com.marmend.clerk.service.{GoogleSheetService, GoogleDriveService, AuthService}
import org.http4s._
import org.http4s.headers._
import org.http4s.MediaType._
import org.http4s.dsl._

object TryMe {

  object CodeQueryParamMatcher extends QueryParamDecoderMatcher[String]("code")

  private val authService = new AuthService()
  private val driveService = new GoogleDriveService()
  private val sheetService = new GoogleSheetService()

  private val emptyCredentials = GoogleCredential("", "", "")

  private var db: Map[User, GoogleCredential] = Map(User("Empty User") -> emptyCredentials)

  val service = HttpService {

    case GET -> Root / "hello" / email =>
      val user = User(email)
      db += (user -> emptyCredentials)
      Ok(s"<p>Please click on link and grant access: <a href=${authService.userUri(user).get}>${email}</a></p>")
        .withContentType(Some(`Content-Type`(`text/html`)))

    case GET -> Root / "hello" / email / access :? CodeQueryParamMatcher(code) =>
      val gCredentials = authService.buildCredential(code, User(email))
      val drive = driveService.getDriveForToken(gCredentials.accessToken)
      val sheet = sheetService.getSheetForToken(gCredentials.accessToken)

      db += (User(email) -> gCredentials)
      Ok(
        s"""This is your access token: ${gCredentials.accessToken}
           |This is your refresh token: ${gCredentials.refreshToken}
           |This is your Drive: ${drive.files().list().size()}
           |This is your Sheet version: ${sheet.getServiceVersion}
         """.stripMargin)

    case GET -> Root / "tokens" / email =>
      Ok(s"${db.getOrElse(User(email), "No tokens")}")

  }


}

