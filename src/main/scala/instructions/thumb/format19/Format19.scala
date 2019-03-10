package instructions.thumb.format19

import instructions.thumb.ThumbInstruction

abstract class Format19 extends ThumbInstruction {
  val h, offset: Int
}

object Format19 {
  def apply(h: String, offset: String): Format19 = {
    val arg0 = Integer.parseInt(h,2)
    val arg1 =
      if (arg0 == 0)
        if (offset.head == '0') Integer.parseInt(offset.tail,2) << 12
        else -((Integer.parseInt(offset.tail,2) ^ 0x7ff) + 1) << 12
      else
        Integer.parseInt(offset,2) << 1
    BL(arg0,arg1)
  }
}
