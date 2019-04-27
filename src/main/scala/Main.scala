import java.nio.ByteBuffer

import control.ROMParser
import instructions.thumb.ThumbInstruction

object Main {
  def main(args: Array[String]): Unit = {
    val rp: ROMParser = new ROMParser
    val rom = rp.importRom("z3prac.gba")
    rom.position(0x386E00)
    for (_ <- 1 to 30) {
      val b = Integer.toBinaryString(rom.getShort() & 0xffff | 0x10000).substring(1)
      //System.out.println(b)
      val t = ThumbInstruction(b)
      System.out.println(t.disassembled())
    }
  }
}
