package control

import java.nio.file.{Files, Paths}
import java.nio.{ByteBuffer, ByteOrder}

class ROMParser {
  def importRom(path: String): ByteBuffer = {
    ByteBuffer.wrap(Files.readAllBytes(Paths.get(path))).order(ByteOrder.LITTLE_ENDIAN)
  }


}
