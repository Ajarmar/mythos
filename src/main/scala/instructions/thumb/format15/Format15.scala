package instructions.thumb.format15

import instructions.thumb.ThumbInstruction

abstract class Format15 extends ThumbInstruction {
  val rb: Byte
  val rlist: List[Byte]
}

object Format15 {
  def apply(l: String, rb: String, rlist: String): Format15 = {
    val arg0 = Integer.parseInt(rb,2).toByte
    val arg1: List[Byte] = rlist.reverse.zipWithIndex.filter(r => r._1.equals('1')).map(_._2.toByte).toList
    l match {
      case "0" => STMIA(arg0,arg1)
      case "1" => LDMIA(arg0,arg1)
    }
  }
}

