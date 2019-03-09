package instructions.thumb.format14

import instructions.thumb.ThumbInstruction

abstract class Format14 extends ThumbInstruction {
  val r: Int
  val rlist: List[Int]
}

object Format14 {
  def apply(l: String, r: String, rlist: String): Format14 = {
    val arg0: Int = Integer.parseInt(r,2)
    val arg1: List[Int] = rlist.reverse.zipWithIndex.filter(r => r._1.equals('1')).map(_._2).toList
    l match {
      case "0" => PUSH(arg0,arg1)
      case "1" => POP(arg0,arg1)
    }
  }
}
