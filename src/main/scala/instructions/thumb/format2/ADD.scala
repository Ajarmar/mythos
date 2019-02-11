package instructions.thumb.format2

case class ADD(i: String, rn_offset3: String, rs: String, rd: String) extends Format2 {
  override val mnemonic: String = "add"
}
