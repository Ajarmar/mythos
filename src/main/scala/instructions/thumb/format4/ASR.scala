package instructions.thumb.format4

case class ASR(rs: Int, rd: Int) extends Format4 {
  override val mnemonic: String = "asr"
}
