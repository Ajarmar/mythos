package control

import simulator.Simulator


class Controller {

  private val rp = new ROMParser
  private var sims: Map[String,Simulator] = Map()

  def initSimulator(filepath: String): Unit = {
    sims += (filepath -> new Simulator(rp.importRom(filepath)))
  }

  def getAllROMInstructions(filepath: String): List[(String,String)] = {
    sims(filepath).getROMInstructionStrings
  }
}
