import java.nio.file.{Files, Paths}

class ROMParser {
  def importRom(path: String): Array[Byte] = {
    Files.readAllBytes(Paths.get(path))
  }


}
