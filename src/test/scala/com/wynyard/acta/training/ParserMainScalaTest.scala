package com.wynyard.acta.training

import org.scalatest.FunSuite

/**
  * Created by wynar2 on 22/09/2016.
  */
class ParserMainScalaTest extends FunSuite {

    test("testParse") {

            val fileLocation: String = "/AccessLogs.txt"
            val main: ParserMainScala = new ParserMainScala
            main.Parse(fileLocation)
            println(fileLocation)

    }

}
