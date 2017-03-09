package com.github.jw3.pc

import scala.util.parsing.combinator._


object Variables extends JavaTokenParsers {

  /**
   * identifier
   */
  def id: Parser[Any] =
    """[a-zA-Z]\w*""".r ^^ (_.toString)


}


