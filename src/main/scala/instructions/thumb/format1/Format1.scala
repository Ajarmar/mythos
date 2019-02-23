package instructions.thumb.format1

import instructions.thumb.ThumbInstruction

abstract class Format1 extends ThumbInstruction {
  val offset5, rs, rd: Int
  override val operands: List[String] = regNameFormat(List(rd,rs)) ++ List(immValueFormat(offset5))
}

object Format1 {
  def apply(op: String, offset5: String, rs: String, rd: String): Format1 = {
    val args: Array[Int] = Array(offset5,rs,rd).map(a => Integer.parseInt(a,2))
    op match {
      case "00" => LSL(args(0),args(1),args(2))
      case "01" => LSR(args(0),args(1),args(2))
      case "10" => ASR(args(0),args(1),args(2))
    }
  }
}