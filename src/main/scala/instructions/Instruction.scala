package instructions

abstract class Instruction {
  def disassembled(): String = this.toString
}
