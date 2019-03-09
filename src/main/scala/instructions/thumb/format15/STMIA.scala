package instructions.thumb.format15

case class STMIA(rb: Int, rlist: List[Int]) extends Format15 {
  override val mnemonic: String = "stmia"
}
