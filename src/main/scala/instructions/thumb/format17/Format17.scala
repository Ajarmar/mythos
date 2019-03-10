package instructions.thumb.format17

import instructions.thumb.ThumbInstruction

abstract class Format17 extends ThumbInstruction {
  val value8: Int
}

object Format17 {
  def apply(value8: String): Format17 = {
    SWI(Integer.parseInt(value8,2))
  }
}
