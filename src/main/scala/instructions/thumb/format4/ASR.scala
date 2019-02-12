package instructions.thumb.format4

case class ASR(rs: String, rd: String) extends Format4 {
  override val mnemonic: String = "asr"
}
