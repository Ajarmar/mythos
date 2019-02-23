package instructions.thumb.format2

import instructions.thumb.ThumbInstruction

abstract class Format2 extends ThumbInstruction {
  val i, rn_offset3, rs, rd: Int
  override val operands: List[String] =
    regNameFormat(List(rd,rs)) ++ (if (i == 0) regNameFormat(List(rn_offset3)) else List(immValueFormat(rn_offset3)))
}

object Format2 {
  def apply(i: String, op: String, rn_offset3: String, rs: String, rd: String): Format2 = {
    val args: Array[Int] = Array(i,rn_offset3,rs,rd).map(a => Integer.parseInt(a,2))
    op match {
      case "0" => ADD(args(0),args(1),args(2),args(3))
      case "1" => SUB(args(0),args(1),args(2),args(3))
    }
  }
}