package instructions.thumb.format16

case class BCC(soffset8: Int) extends Format16 {
  override val mnemonic: String = "bcc"
}
