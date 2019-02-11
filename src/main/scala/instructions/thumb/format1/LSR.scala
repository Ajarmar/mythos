package instructions.thumb.format1

case class LSR(offset5: String, rs: String, rd: String) extends Format1 {
  override val mnemonic: String = "lsr"
}
