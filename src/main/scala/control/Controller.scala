package control

import simulator.Simulator


class Controller {

  private val rp = new ROMParser
  private var sims: Map[String,Simulator] = Map()

  def initSimulator(filepath: String): Unit = {
    val start = System.currentTimeMillis()
    sims += (filepath -> new Simulator(rp.importRom(filepath)))
    val end = System.currentTimeMillis()
    System.out.println("Time: " + (end - start))
  }

  def getROMInstructions(filepath: String, start: Int, end: Int): List[(String,String,String)] = {
    sims(filepath).getThumbInstructionStrings(start,end)
  }
}
