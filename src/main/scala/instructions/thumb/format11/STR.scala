package instructions.thumb.format11

case class STR(rd: String, word8: String) extends Format11 {
  override val mnemonic: String = "str"
}
