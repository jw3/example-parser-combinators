package com.github.jw3.pc

import scala.util.parsing.combinator._

/*

value ::= array | boolean | number | string

array ::= "[" [values] "]"

boolean ::= "true" | "false"

number ::= floatingPointNumber

string ::= stringLiteral

values ::= value {"," value}

 */

case class Assign(id: String)


class Lang extends JavaTokenParsers {
  def id: Parser[Any] = """[a-zA-Z]\w*""".r

  def assign: Parser[Any] = "=" ^^ (v => Assign(v))
  def equality: Parser[Any] = "=="
  def op: Parser[Any] = equality | assign

  def expr1: Parser[Any] =
    id ~ op ~ expr1 |
      `if` |
      id |
      value

  def expr: Parser[Any] = expr1 ~ ";"
  def exprs: Parser[Any] = rep(expr)
  def block: Parser[Any] = "{" ~ exprs ~ "}"

  def booleanLiteral: Parser[Any] = "true" ^^ (_ => true) | "false" ^^ (_ => false)
  def value: Parser[Any] = booleanLiteral | floatingPointNumber ^^ (v => v.toDouble) | stringLiteral

  def `if`: Parser[Any] = "if" ~ "(" ~ expr1 ~ ")" ~ "then" ~ block
}


object ParseExpr extends Lang {
  def main(args: Array[String]) = {
    Seq(
      """x = "bam";""",
      """ if(true) then { 1; } ;""",
      """ a==b; """,
      """ if(true) then { 1 } ;"""   // should fail, missing trailing semicolon
    .stripMargin
    ).foreach(input => println(parseAll(expr, input)))
  }
}

//[1.6] parsed: ((1~List())~List((+~(1~List()))))
