name := """play_23"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  "com.typesafe.play" %% "play-slick" % "0.8.1",
  "com.h2database" % "h2" % "1.4.187",
  jdbc,
  anorm,
  cache,
  ws
)
