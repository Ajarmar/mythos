package instructions.arm

case class CoDataOperation(cond: Int, cpOpcode: Int, cRn: Int, cRd: Int, cpNum: Int, cp: Int, cRm: Int) extends ArmInstruction {

}
