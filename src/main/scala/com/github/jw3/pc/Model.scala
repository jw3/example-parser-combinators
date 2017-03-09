package com.github.jw3.pc


trait Renderable {
  def render(): String
}

trait Returns[T <: SqfType] extends Renderable

/**
 * An expression is a piece of code that returns a value.
 *
 * variable
 * operation
 * scripting command
 * control structure
 */
trait Expression extends Renderable


trait Variable[T <: SqfType] extends Expression with Returns[T]
trait Command[T <: SqfType] extends Expression
trait Control extends Expression


trait Operation extends Expression
trait UnaryOperation[T <: SqfType] extends Expression
trait BinaryOperation[L <: SqfType, R <: SqfType] extends Expression


trait SqfType
trait SqfNothing extends SqfType with SqfAny
trait SqfString extends SqfType with SqfAny
trait SqfNumber extends SqfType with SqfAny
trait SqfAny extends SqfType


case class StringVariable(name: String) extends Variable[SqfString] {def render() = name}
case class NumberVariable(name: Number) extends Variable[SqfNumber] {def render() = name.toString}


case class HintCommand(in: Returns[SqfString]) extends Command[SqfNothing] {
  def render(): String = s"hint ${in.render()}"
}

case class FormatCommand(in: String, args: Returns[_ <: SqfAny]*) extends Command[SqfString] {
  def render(): String = s"""format ["$in", ${args.map(_.render()).mkString(",")}]"""
}

object foo extends App {
  val v = StringVariable("name")
  println(FormatCommand("hello %1", v).render())







}

trait Identifier {
  def name: String
}

//
//trait DataType
//case class NumberType() extends DataType
//case class StringType() extends DataType
//
//trait Variable[T <: DataType] extends Expression with Identifier {
//  def render(): String = name
//}
//
//case class NumberVariable(name: String) extends Variable[NumberType]
//case class StringVariable(name: String) extends Variable[StringType]
//
//// end with ;
//abstract class Statement
//
//abstract class Assignment extends Statement
//abstract class ControlStruct extends Statement
//abstract class Command extends Statement
//
//case class SimpleAssignment(lhs: Identifier, rhs: Statement)
//
//case class Block(stmts: Seq[Statement]) extends Statement
//
//
//object Foo extends App {
//
//
//  val v = NumberVariable("foo")
//  matchvar(v)
//
//
//  def matchvar(v: Variable[_]) = {
//    v match {
//      case v: StringVariable => println(s"string var: ${v.render()}")
//      case v: NumberVariable => println(s"number var: ${v.render()}")
//    }
//  }
//
//}
