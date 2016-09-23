package com.wynyard.acta.training

import java.io.{BufferedReader, InputStream, InputStreamReader}
import java.time.LocalDateTime

import scala.collection.mutable.ArrayBuffer

/**
  * Created by wynar2 on 22/09/2016.
  */

class ParserMainScala {

    private var parsedEvents: ArrayBuffer[ParsedEventScala] = ArrayBuffer()



    private def GetSource(location: String): InputStream = {
        val readData: InputStream = this.getClass.getResourceAsStream(location)
        readData
    }

    @throws[Exception]
    def Parse(location: String): Array[ParsedEventScala] = {

        val readData: InputStream = GetSource(location)

        if (readData == null) throw new Exception("Error Occured")

        val stream: BufferedReader = new BufferedReader(new InputStreamReader(readData))
       // val line1: String = stream.readLine
       // println("Testing.."+line1)
        var isEmpty: Boolean = true

        while (isEmpty) {
            try {
                val line: String = stream.readLine
                if (filter(line)) {
                    val items: Array[String] = line.split(" ")
                    val event: ParsedEventScala = new ParsedEventScala().ConstructAndReturnParsedEvent(items)
                    if (event.timeStamp ne LocalDateTime.MIN) parsedEvents += event
                }

            }

            catch {
                case ex: Exception => {
                    isEmpty = false
                }
            }

        }
        parsedEvents.toArray
    }
    private def filter(line: String): Boolean = {

        if (line.startsWith("date") == true) false else true
    }
}
