package instructions.arm

case class CoDataTransfer(cond: Int, p: Int, u: Int, n: Int, w: Int, l: Int, rn: Int, cRd: Int, cpNum: Int, offset: Int) extends ArmInstruction {

}
