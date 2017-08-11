package com.marmend.clerk.service

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.jackson2.JacksonFactory
import com.google.gdata.client.spreadsheet.SpreadsheetService
import com.google.gdata.data.spreadsheet.{CellEntry, CellFeed, WorksheetEntry, SpreadsheetEntry}
import com.marmend.clerk.model._

/**
 * Created by maksim on 8/10/17.
 */
class GoogleSheetService {

  lazy val app = Json.fromJson[GoogleApp](this.getClass.getClassLoader.getResource("google_secrets.json"))

  private val sheetsFeedBase = "https://spreadsheets.google.com/feeds/spreadsheets/"

  def getSheetForToken(accessToken: String) = {
    val service = new SpreadsheetService("beekeeper")
    val httpTransport = new NetHttpTransport
    val jsonFactory = new JacksonFactory

    val credential = new GoogleCredential.Builder()
      .setJsonFactory(jsonFactory)
      .setTransport(httpTransport)
      .setClientSecrets(app.clientId, app.clientSecret)
      .build()
    credential.setAccessToken(accessToken)

    service.setHeader("Authorization", "Bearer " + accessToken)

    service.setOAuth2Credentials(credential)

    service
  }

}
