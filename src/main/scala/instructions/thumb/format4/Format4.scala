package instructions.thumb.format4

import instructions.thumb.ThumbInstruction

abstract class Format4 extends ThumbInstruction {
  val rs, rd: Int
  override val operands: List[String] = regNameFormat(List(rd,rs))
}

object Format4 {
  def apply(op: String, rs: String, rd: String): Format4 = {
    val args: Array[Int] = Array(rs,rd).map(a => Integer.parseInt(a,2))
    op match {
      case "0000" => AND(args(0),args(1))
      case "0001" => EOR(args(0),args(1))
      case "0010" => LSL(args(0),args(1))
      case "0011" => LSR(args(0),args(1))
      case "0100" => ASR(args(0),args(1))
      case "0101" => ADC(args(0),args(1))
      case "0110" => SBC(args(0),args(1))
      case "0111" => ROR(args(0),args(1))
      case "1000" => TST(args(0),args(1))
      case "1001" => NEG(args(0),args(1))
      case "1010" => CMP(args(0),args(1))
      case "1011" => CMN(args(0),args(1))
      case "1100" => ORR(args(0),args(1))
      case "1101" => MUL(args(0),args(1))
      case "1110" => BIC(args(0),args(1))
      case "1111" => MVN(args(0),args(1))
    }
  }
}

