package instructions.thumb.format4

import instructions.thumb.ThumbInstruction

abstract class Format4 extends ThumbInstruction {
  val rs, rd: String
  override val operands: List[String] = regNameFormat(List(rd,rs))
}

object Format4 {
  def apply(op: String, rs: String, rd: String): Format4 = op match {
    case "0000" => AND(rs,rd)
    case "0001" => EOR(rs,rd)
    case "0010" => LSL(rs,rd)
    case "0011" => LSR(rs,rd)
    case "0100" => ASR(rs,rd)
    case "0101" => ADC(rs,rd)
    case "0110" => SBC(rs,rd)
    case "0111" => ROR(rs,rd)
    case "1000" => TST(rs,rd)
    case "1001" => NEG(rs,rd)
    case "1010" => CMP(rs,rd)
    case "1011" => CMN(rs,rd)
    case "1100" => ORR(rs,rd)
    case "1101" => MUL(rs,rd)
    case "1110" => BIC(rs,rd)
    case "1111" => MVN(rs,rd)
  }
}

