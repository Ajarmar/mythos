package instructions.arm

case class DataProcessing(cond: Int, i: Int, opcode: Int, s: Int, rn: Int, rd: Int, operand2: Int) extends ArmInstruction {

}
