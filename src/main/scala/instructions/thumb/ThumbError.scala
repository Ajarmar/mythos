package instructions.thumb

case class ThumbError(error: ThumbError.Value) extends ThumbInstruction {

}

object ThumbError extends Enumeration {
  val NotAnInstruction, OutOfBounds = Value
}