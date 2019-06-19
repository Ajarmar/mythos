package instructions.thumb.format5

import instructions.thumb.ThumbInstruction

abstract class Format5 extends ThumbInstruction {
  val h1, h2, rs_hs, rd_hd: Byte
}

object Format5 {
  def apply(op: String, h1: String, h2: String, rs_hs: String, rd_hd: String): Format5 = {
    val args: Array[Byte] = Array(h1,h2,rs_hs,rd_hd).map(a => Integer.parseInt(a,2).toByte)
    op match {
      case "00" => ADD(args(0),args(1),args(2),args(3))
      case "01" => CMP(args(0),args(1),args(2),args(3))
      case "10" => MOV(args(0),args(1),args(2),args(3))
      case "11" => BX(args(0),args(1),args(2),args(3))
    }
  }

}
