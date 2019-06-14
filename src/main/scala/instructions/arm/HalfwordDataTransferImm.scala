package instructions.arm

case class HalfwordDataTransferImm(cond: Int, p: Int, u: Int, w: Int, l: Int, rn: Int, rd: Int, offsetHi: Int, s: Int, h: Int, offsetLo: Int) extends ArmInstruction {

}
