package instructions.thumb

case class ThumbError(error: ThumbError.Value) extends ThumbInstruction {
  override val mnemonic: String = "-"
}

object ThumbError extends Enumeration {
  val NotAnInstruction, OutOfBounds = Value
}