package instructions.thumb.format16

import instructions.thumb.ThumbInstruction

abstract class Format16 extends ThumbInstruction {
  val soffset8: Int
  override val specialFormatting: Option[(Int => String) => String] =
    Some((pcFunc: Int => String) => disassembled().replaceAll("\\[.*\\]",pcFunc(soffset8)))
}

object Format16 {
  def apply(cond: String, soffset8: String): Format16 = {
    val arg0 =
      if (soffset8.head == '0') Integer.parseInt(soffset8.tail,2) << 1
      else -((Integer.parseInt(soffset8.tail,2) ^ 0x7f) + 1) << 1
    cond match {
      case "0000" => BEQ(arg0)
      case "0001" => BNE(arg0)
      case "0010" => BCS(arg0)
      case "0011" => BCC(arg0)
      case "0100" => BMI(arg0)
      case "0101" => BPL(arg0)
      case "0110" => BVS(arg0)
      case "0111" => BVC(arg0)
      case "1000" => BHI(arg0)
      case "1001" => BLS(arg0)
      case "1010" => BGE(arg0)
      case "1011" => BLT(arg0)
      case "1100" => BGT(arg0)
      case "1101" => BLE(arg0)
      /*case _ => */
    }
  }
}
