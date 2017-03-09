name := "example-parser-combinator"

version := "0.1"

scalaVersion := "2.11.8"


libraryDependencies ++= {
  val scalatestVersion = "3.0.1"

  Seq(
    "org.scala-lang.modules" %% "scala-parser-combinators" % "1.0.5",
    "org.scalactic" %% "scalactic" % scalatestVersion % Test,
    "org.scalatest" %% "scalatest" % scalatestVersion % Test
  )
}
