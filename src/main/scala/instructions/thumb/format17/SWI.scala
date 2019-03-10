package instructions.thumb.format17

case class SWI(value8: Int) extends Format17 {
  override val mnemonic: String = "swi"
}
