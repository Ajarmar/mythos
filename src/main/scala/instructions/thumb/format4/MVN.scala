package instructions.thumb.format4

case class MVN(rs: Int, rd: Int) extends Format4 {
  override val mnemonic: String = "mvn"
}
