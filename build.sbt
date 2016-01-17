import play.PlayJava

name := """Video Playlist"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  javaWs,
  "com.h2database" % "h2" % "1.4.181",
  "org.springframework" % "spring-context" % "4.1.1.RELEASE",
  "org.springframework" % "spring-orm" % "4.1.1.RELEASE",
  "org.springframework" % "spring-jdbc" % "4.1.1.RELEASE",
  "org.springframework" % "spring-tx" % "4.1.1.RELEASE",
  "org.springframework" % "spring-expression" % "4.1.1.RELEASE",
  "org.springframework" % "spring-aop" % "4.1.1.RELEASE",
  "org.springframework" % "spring-test" % "4.1.1.RELEASE" % "test",
  "org.webjars" % "jquery" % "1.11.2",
  "org.webjars" % "bootstrap" % "2.1.1",
  "mysql" % "mysql-connector-java" % "5.1.18",
  "org.hibernate" % "hibernate-entitymanager" % "4.3.9.Final"
)