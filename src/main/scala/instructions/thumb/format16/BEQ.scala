package instructions.thumb.format16

case class BEQ(soffset8: Int) extends Format16 {
  override val mnemonic: String = "beq"
}
