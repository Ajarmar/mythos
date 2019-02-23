import instructions.thumb.ThumbInstruction
import instructions.thumb.format1.LSL
import org.scalatest.FunSuite

class ThumbInstructionTest extends FunSuite {

  test("Format1.apply") {
    val lslString = "0000000111000001"
    val ti = ThumbInstruction(lslString)
    val lsl = LSL(7,0,1)
    val otherLsl = LSL(7,1,6)
    assert(ti.equals(lsl))
    assert(!ti.equals(otherLsl))
  }
}
