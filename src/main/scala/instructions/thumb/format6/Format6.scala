package instructions.thumb.format6

import instructions.thumb.ThumbInstruction

abstract class Format6 extends ThumbInstruction {
  val rd, word8: Short
}

object Format6 {
  def apply(rd: String, word8: String): Format6 = {
    val args: Array[Short] = Array(rd,word8).map(a => Integer.parseInt(a,2).toShort)
    LDR(args(0),args(1))
  }
}
