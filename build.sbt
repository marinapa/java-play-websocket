name := "test-app"

version := "1.0"

lazy val `test-app` = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.6"

libraryDependencies ++= Seq( cache, javaWs )

libraryDependencies ++= Seq(
  javaJdbc,
  javaJpa.exclude("org.hibernate.javax.persistence", "hibernate-jpa-2.0-api"),
  "org.hibernate" % "hibernate-entitymanager" % "4.3.0.Final",
  "org.assertj" % "assertj-core" % "3.1.0" % "test",
  "org.apache.commons" % "commons-lang3" % "3.4",
  "org.springframework" % "spring-context" % "4.2.4.RELEASE",
  "org.springframework" % "spring-aop" % "4.2.4.RELEASE",
  "org.springframework" % "spring-expression" % "4.2.4.RELEASE"
)

libraryDependencies ++= Seq( "org.postgresql" % "postgresql" % "9.4-1200-jdbc41" )

libraryDependencies ++= Seq( "mysql" % "mysql-connector-java" % "5.1.36" )


unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )  