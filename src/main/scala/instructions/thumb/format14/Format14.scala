package instructions.thumb.format14

import instructions.thumb.ThumbInstruction

abstract class Format14 extends ThumbInstruction {
  val r: Byte
  val rlist: List[Byte]
}

object Format14 {
  def apply(l: String, r: String, rlist: String): Format14 = {
    val arg0: Byte = Integer.parseInt(r,2).toByte
    val arg1: List[Byte] = rlist.reverse.zipWithIndex.filter(r => r._1.equals('1')).map(_._2.toByte).toList
    l match {
      case "0" => PUSH(arg0,arg1)
      case "1" => POP(arg0,arg1)
    }
  }
}
