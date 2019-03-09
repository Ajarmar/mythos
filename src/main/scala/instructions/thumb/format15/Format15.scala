package instructions.thumb.format15

import instructions.thumb.ThumbInstruction

abstract class Format15 extends ThumbInstruction {
  val rb: Int
  val rlist: List[Int]
  override val operands: List[String] = regNameFormat(List(rb)).map(r => r.concat("!"))
}

object Format15 {
  def apply(l: String, rb: String, rlist: String): Format15 = {
    val arg0 = Integer.parseInt(rb)
    val arg1: List[Int] = rlist.reverse.zipWithIndex.filter(r => r._1.equals('1')).map(_._2).toList
    l match {
      case "0" => STMIA(arg0,arg1)
      case "1" => LDMIA(arg0,arg1)
    }
  }
}

