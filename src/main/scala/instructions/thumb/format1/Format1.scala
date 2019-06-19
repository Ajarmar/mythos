package instructions.thumb.format1

import instructions.thumb.ThumbInstruction

abstract class Format1 extends ThumbInstruction {
  val offset5, rs, rd: Byte
}

object Format1 {
  def apply(op: Byte, offset5: Byte, rs: Byte, rd: Byte): Format1 = {
    op match {
      case 0 => LSL(offset5,rs,rd)
      case 1 => LSR(offset5,rs,rd)
      case 2 => ASR(offset5,rs,rd)
    }
  }
  def apply(op: String, offset5: String, rs: String, rd: String): Format1 = {
    val args: Array[Byte] = Array(offset5,rs,rd).map(a => Integer.parseInt(a,2).toByte)
    op match {
      case "00" => LSL(args(0),args(1),args(2))
      case "01" => LSR(args(0),args(1),args(2))
      case "10" => ASR(args(0),args(1),args(2))
    }
  }
}