package instructions.arm

case class SingleDataTransfer(cond: Int, i: Int, p: Int, u: Int, b: Int, w: Int, l: Int, rn: Int, rd: Int, offset: Int) extends ArmInstruction {

}
