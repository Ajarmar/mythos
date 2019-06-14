package instructions.arm

case class BlockDataTransfer(cond: Int, p: Int, u: Int, s: Int, w: Int, l: Int, rn: Int, regList: Int) extends ArmInstruction {

}
