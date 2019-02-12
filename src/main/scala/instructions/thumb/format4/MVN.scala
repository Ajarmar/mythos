package instructions.thumb.format4

case class MVN(rs: String, rd: String) extends Format4 {
  override val mnemonic: String = "mvn"
}
