package instructions.thumb.format4

case class CMP(rs: Int, rd: Int) extends Format4 {
  override val mnemonic: String = "cmp"
}
