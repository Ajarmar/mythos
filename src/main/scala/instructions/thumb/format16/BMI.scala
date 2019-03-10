package instructions.thumb.format16

case class BMI(soffset8: Int) extends Format16 {
  override val mnemonic: String = "bmi"
}
