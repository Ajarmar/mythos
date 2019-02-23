package instructions.thumb.format4

case class ADC(rs: Int, rd: Int) extends Format4 {
  override val mnemonic: String = "adc"
}
