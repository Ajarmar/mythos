package instructions.thumb.format11

case class STR(rd: Int, word8: Int) extends Format11 {
  override val mnemonic: String = "str"
}
