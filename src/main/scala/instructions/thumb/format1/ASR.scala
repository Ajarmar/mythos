package instructions.thumb.format1

case class ASR(offset5: String, rs: String, rd: String) extends Format1(offset5: String, rs: String, rd: String) {
  override val mnemonic: String = "asr"
}
