organization := "com.marmend"
name := "clerk"
version := "0.0.1-SNAPSHOT"
scalaVersion := "2.12.2"

val Http4sVersion = "0.17.0-M2"

resolvers += "Sonatype OSS" at "https://oss.sonatype.org/content/repositories/iolemonlabs-1004"

libraryDependencies ++= Seq(
  "org.http4s" %% "http4s-blaze-server" % Http4sVersion,
  "org.http4s" %% "http4s-circe" % Http4sVersion,
  "org.http4s" %% "http4s-dsl" % Http4sVersion,
  "ch.qos.logback" % "logback-classic" % "1.2.1",
  "com.google.gdata" % "core" % "1.47.1",
  "com.google.apis" % "google-api-services-oauth2" % "v2-rev128-1.22.0",
  "com.google.apis" % "google-api-services-drive" % "v3-rev81-1.22.0",
  "com.fasterxml.jackson.module" % "jackson-module-scala_2.12" % "2.9.0",
  "com.fasterxml.jackson.datatype" % "jackson-datatype-joda" % "2.9.0",
  "org.scalaj" %% "scalaj-http" % "2.3.0"
).map(
  _.exclude("org.mortbay.jetty", "jetty")
    .exclude("org.mortbay.jetty", "servlet-api")
    .exclude("org.mortbay.jetty", "jetty-util")
)
unmanagedResourceDirectories in Runtime := Seq(baseDirectory.value / "src")
