package instructions.thumb.format12

import instructions.thumb.ThumbInstruction

abstract class Format12 extends ThumbInstruction {
  val sp, rd, word8: Short

}

object Format12 {
  def apply(sp: String, rd: String, word8: String): Format12 = {
    val args: Array[Short] = Array(sp,rd,word8).map(a => Integer.parseInt(a,2).toShort)
    ADD(args(0),args(1),args(2))
  }
}
