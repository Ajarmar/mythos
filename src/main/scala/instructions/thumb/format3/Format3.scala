package instructions.thumb.format3

import instructions.thumb.ThumbInstruction

abstract class Format3 extends ThumbInstruction {
  val rd, offset8: Int
  override val operands: List[String] = regNameFormat(List(rd)) ++ List(immValueFormat(offset8))
}

object Format3 {
  def apply(op: String, rd: String, offset8: String): Format3 = {
    val args: Array[Int] = Array(rd,offset8).map(a => Integer.parseInt(a,2))
    op match {
      case "00" => MOV(args(0),args(1))
      case "01" => CMP(args(0),args(1))
      case "10" => ADD(args(0),args(1))
      case "11" => SUB(args(0),args(1))
    }
  }
}