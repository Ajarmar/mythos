package instructions.thumb.format16

case class BLE(soffset8: Int) extends Format16 {
  override val mnemonic: String = "ble"
}
