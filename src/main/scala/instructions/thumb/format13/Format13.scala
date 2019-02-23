package instructions.thumb.format13

import instructions.thumb.ThumbInstruction

abstract class Format13 extends ThumbInstruction {
  val s, sword7: Int
  override val operands: List[String] = List("SP",immValueFormat(if (s == 0) sword7 << 2 else -(sword7 << 2)))
}

object Format13 {
  def apply(s: String, sword7: String): Format13 = {
    val args: Array[Int] = Array(s,sword7).map(a => Integer.parseInt(a,2))
    ADD(args(0), args(1))
  }
}
