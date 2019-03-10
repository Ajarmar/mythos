package instructions.thumb.format16

import instructions.thumb.ThumbInstruction

abstract class Format16 extends ThumbInstruction {
  val soffset8: Int
  override val specialFormatting: Option[(Int => String) => String] =
    Some((pcFunc: Int => String) => disassembled().replaceAll("\\[.*\\]",pcFunc(soffset8)))
}

object Format16 {
  def apply(cond: String, soffset8b: String): Format16 = {
    val soffset8 =
      (if (soffset8b.head == '0') Integer.parseInt(soffset8b.tail) << 1
      else -((Integer.parseInt(soffset8b.tail) ^ 0xff) + 1)) << 1
    cond match {
      case "0000" => BEQ(soffset8)
      case "0001" => BNE(soffset8)
      case "0010" => BCS(soffset8)
      case "0011" => BCC(soffset8)
      case "0100" => BMI(soffset8)
      case "0101" => BPL(soffset8)
      case "0110" => BVS(soffset8)
      case "0111" => BVC(soffset8)
      case "1000" => BHI(soffset8)
      case "1001" => BLS(soffset8)
      case "1010" => BGE(soffset8)
      case "1011" => BLT(soffset8)
      case "1100" => BGT(soffset8)
      case "1101" => BLE(soffset8)
      /*case _ => */
    }
  }
}
