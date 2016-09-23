package com.wynyard.acta.training

/**
  * Created by wynar2 on 22/09/2016.
  */
class RunnerScala {
    @throws[Exception]
    def main(args: Array[String]) {
        val fileLocation: String = "/AccessLogs.txt"
        val main: ParserMainScala = new ParserMainScala
        main.Parse(fileLocation)
    }
}
