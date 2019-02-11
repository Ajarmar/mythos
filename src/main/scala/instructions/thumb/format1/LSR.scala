package instructions.thumb.format1

case class LSR(offset5: String, rs: String, rd: String) extends Format1(offset5: String, rs: String, rd: String) {
  override val mnemonic: String = "lsr"
}
