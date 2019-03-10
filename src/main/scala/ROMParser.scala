import java.nio.{ByteBuffer, ByteOrder}
import java.nio.file.{Files, Paths}

class ROMParser {
  def importRom(path: String): ByteBuffer = {
    ByteBuffer.wrap(Files.readAllBytes(Paths.get(path))).order(ByteOrder.LITTLE_ENDIAN)
  }


}
