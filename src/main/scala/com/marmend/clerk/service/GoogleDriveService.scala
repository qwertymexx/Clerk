package com.marmend.clerk.service

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.jackson2.JacksonFactory
import com.google.api.services.drive.Drive
import com.marmend.clerk.model.{GoogleApp}


/**
 * Created by maksim on 8/10/17.
 */
class GoogleDriveService {
  lazy val app = Json.fromJson[GoogleApp](this.getClass.getClassLoader.getResource("google_secrets.json"))

  def getDriveForToken(accessToken: String): Drive = {

    val httpTransport = new NetHttpTransport
    val jsonFactory = new JacksonFactory

    //Build the Google credentials and make the Drive ready to interact
    val credential = new GoogleCredential.Builder()
      .setJsonFactory(jsonFactory)
      .setTransport(httpTransport)
      .setClientSecrets(app.clientId, app.clientSecret)
      .build()
    credential.setAccessToken(accessToken)
    //Create a new authorized API client
    new Drive.Builder(httpTransport, jsonFactory, credential).build()
  }

}
