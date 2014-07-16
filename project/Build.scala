import sbt._
import sbt.Keys._
import play.Project._


object Build extends Build {

  lazy val gallops = Project(
    id = "gallops",
    base = file("."),
    settings = defaultSettings,
    aggregate = Seq(commons, features, dashboard)
  )

  lazy val commons = Project(
    id = "gallops-commons",
    base = file("gallops-commons"),
    settings = defaultSettings ++ Seq(
      libraryDependencies ++= Dependencies.commons
    )
  )

  lazy val features = Project(
    id = "gallops-features",
    base = file("gallops-features"),
    settings = defaultSettings ++ Seq(
      libraryDependencies ++= Dependencies.features
    )
  )

  lazy val dashboard = play.Project("gallops-dashboard", "1,0-SNAPSHOT", path = file("gallops-dashboard"), settings = defaultSettings ++ Seq(
     conflictWarning := ConflictWarning.disable,
     libraryDependencies ++= Dependencies.dashboard
  )) dependsOn(commons, features) aggregate(commons, features)

  lazy val defaultSettings = Defaults.defaultSettings ++ Seq(
    scalaVersion := "2.10.3",
    scalacOptions ++= Seq("-encoding", "UTF-8", "-deprecation", "-unchecked"),
    resolvers += "Typesafe Repo" at "http://repo.typesafe.com/typesafe/releases/"
  )
}

object Dependencies {

  import Dependency._

  val commons = Seq(
    scalaTest, logbackCore, logbackClassic
  )

  val features = Seq(
    typesafeConfig, scalaTest
  )

  val dashboard = Seq()
}

object Dependency {

  val typesafeConfig = "com.typesafe" % "config" % "1.2.0"

  val logbackCore = "ch.qos.logback" % "logback-core" % "1.0.12"
  val logbackClassic = "ch.qos.logback" % "logback-classic" % "1.0.12"

  val scalaTest = "org.scalatest" %% "scalatest" % "2.2.0" % "test"
}
