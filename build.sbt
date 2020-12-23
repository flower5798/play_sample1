name := """play-sample1"""
organization := "net.jp.engineering"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

//libraryDependencies += "com.google.code.gson" %% "gson" % "2.2.4"

libraryDependencies ++= Seq(
  javaJdbc,
//  javaEbean, // 参照：https://stackoverflow.com/questions/42225731/cant-configure-ebean-play-framework-2-5-x
//  cache,
  filters, //追加
  "org.jongo" % "jongo" % "1.0", // 追加
  "org.mongodb" % "mongo-java-driver" % "2.11.4", // 追加
  "com.google.code.gson" % "gson" % "2.2.4"
)

scalaVersion := "2.13.3"

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test


//scalaVersion := "2.13.3"
//libraryDependencies += "com.google.code.gson" %% "gson_2.2.4" % "5.0.0"

//libraryDependencies ++= Seq(
//  "com.google.code.gson" %% "gson" % "2.2.4"
//)


// Adds additional packages into Twirl
//TwirlKeys.templateImports += "net.jp.engineering.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "net.jp.engineering.binders._"
