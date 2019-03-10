package instructions.thumb.format16

case class BGE(soffset8: Int) extends Format16 {
  override val mnemonic: String = "bge"
}
