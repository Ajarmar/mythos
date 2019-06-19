package instructions.thumb.format18

import instructions.thumb.ThumbInstruction

abstract class Format18 extends ThumbInstruction {
  val offset11: Short
}

object Format18 {
  def apply(offset11: String): Format18 = {
    val arg0 =
      if (offset11.head == '0') Integer.parseInt(offset11.tail,2) << 1
      else -((Integer.parseInt(offset11.tail,2) ^ 0x3ff) + 1) << 1
    B(arg0.toShort)
  }
}
