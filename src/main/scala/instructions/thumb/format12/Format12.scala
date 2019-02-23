package instructions.thumb.format12

import instructions.thumb.ThumbInstruction

abstract class Format12 extends ThumbInstruction {
  val sp, rd, word8: Int
  override val operands: List[String] = regNameFormat(List(rd)) ++ List(if (sp == 1) "SP" else "PC") ++ List(immValueFormat(word8))

}

object Format12 {
  def apply(sp: String, rd: String, word8: String): Format12 = {
    val args: Array[Int] = Array(sp,rd,word8).map(a => Integer.parseInt(a,2))
    ADD(args(0),args(1),args(2))
  }
}
