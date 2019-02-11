package instructions.thumb.format2

case class SUB(i: String, rn_offset3: String, rs: String, rd: String) extends Format2 {
  override val mnemonic: String = "sub"
}
