package instructions.thumb.format4

case class BIC(rs: Int, rd: Int) extends Format4 {
  override val mnemonic: String = "bic"
}
