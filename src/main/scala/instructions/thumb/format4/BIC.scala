package instructions.thumb.format4

case class BIC(rs: String, rd: String) extends Format4 {
  override val mnemonic: String = "bic"
}
