ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.2.1"

lazy val root = (project in file("."))
  .settings(
    name := "ddos-SoC"
  )

val AkkaVersion = "2.7.0"

libraryDependencies +=
  "com.typesafe.akka" %% "akka-actor" % AkkaVersion

//libraryDependencies +=
//  "com.typesafe.akka" %% "akka-actor-typed" % AkkaVersion

libraryDependencies +=
  "com.typesafe.akka" %% "akka-remote" % AkkaVersion