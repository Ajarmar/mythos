package instructions.arm

case class CoRegTransfer(cond: Int, cpOpcode: Int, l: Int, cRn: Int, rd: Int, cpNum: Int, cp: Int, cRm: Int) extends ArmInstruction {

}
