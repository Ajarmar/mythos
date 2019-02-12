package instructions.thumb.format4

case class CMN(rs: String, rd: String) extends Format4 {
  override val mnemonic: String = "cmn"
}
