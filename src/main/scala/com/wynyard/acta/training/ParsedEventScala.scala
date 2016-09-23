package com.wynyard.acta.training

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
  * Created by wynar2 on 22/09/2016.
  */
class ParsedEventScala {

    private[training] var timeTaken: Option[String] = None
    //   private[training] var ipAddress: Option[String] = None
    private[training] var username: Option[String] = None
    private[training] var status: String = ""
    private[training] var timeStamp: LocalDateTime = LocalDateTime.MIN
    private[training] var dateTime: String = ""
    //private[training] var validateIP: String = ""
    private[training] var ipAddress: String = ""

    /**
      * Accepts parsed strings from current line and converts into a valid ParsedEventScala
      *
      * @param parsedItems String of arrays
      * @return ParsedEventScala
      */

    def ConstructAndReturnParsedEvent(parsedItems: Array[String]): ParsedEventScala = {

        for ((singleItem, counter) <- parsedItems.zipWithIndex) {
            try {
                counter match {
                    case 0 => dateTime = singleItem
                    case 1 => {
                        dateTime += " " + singleItem
                        timeStamp = TryParseDateTime(dateTime)
                    }
                    case 2 => timeTaken = Some(singleItem)
                    case 3 => {
                        // validateIP =
                        ipAddress = TryParseIp(singleItem)
                        println(ipAddress)
                    }
                    case 4 => username = Some(singleItem)
                    case 5 => status = singleItem
                }
            } catch {
                case ex: Exception => {
                    ex.printStackTrace()
                }
            }

        }

        this
    }

    @throws[Exception]
    private def TryParseDateTime(dateTime: String): LocalDateTime = {
        val dateTimeFormatTobeUsed: String = "yyyy-MM-dd HH:mm:ss"
        try {
            LocalDateTime.parse(dateTime.trim, DateTimeFormatter.ofPattern(dateTimeFormatTobeUsed))
        }
        catch {
            case ex: Exception => {
                throw new Exception("Unable to parse date time")
            }
        }
    }

    /**
      *
      * @param ipAddress
      * @throws exception
      * @return ipAddress
      */
    @throws[Exception]
    private def TryParseIp(ipAddress: String): String = {

        val IP4_PATTERN: String = "\\b(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)){3}\\b"
        try {
            if (ipAddress.trim.matches(IP4_PATTERN)) {
                ipAddress
            } else {
                throw new Exception("This is not proper IP 4 format")
            }
        } catch {
            case ex: Exception => {
                throw new Exception("Unable to parse ip Address [%s]".format(ipAddress))
            }
        }


    }

    /**
      * New verifications for username
      */
}
