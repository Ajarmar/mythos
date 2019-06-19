package instructions.thumb.format13

import instructions.thumb.ThumbInstruction

abstract class Format13 extends ThumbInstruction {
  val s, sword7: Byte
}

object Format13 {
  def apply(s: String, sword7: String): Format13 = {
    val args: Array[Byte] = Array(s,sword7).map(a => Integer.parseInt(a,2).toByte)
    ADD(args(0), args(1))
  }
}
