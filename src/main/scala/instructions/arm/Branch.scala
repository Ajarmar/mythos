package instructions.arm

case class Branch(cond: Int, l: Int, offset: Int) extends ArmInstruction {

}
