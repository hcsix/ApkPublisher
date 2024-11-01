package com.supcoder.publisher

import com.supcoder.publisher.command.Command
import com.supcoder.publisher.status.TaskSession
import java.util.*


fun main(args: Array<String>) {
    val session = TaskSession
    val scanner = Scanner(System.`in`)
    println(session.getStatus().getHint())
    while (scanner.hasNextLine()) {
        val input = scanner.nextLine()
        when (input.lowercase()) {
            Command.EXIT.lowercase() -> {
                println("Exiting...")
                break
            }
            else -> {
                val isSuccess = session.parseCommand(input)
//                if (!isSuccess){
//                    println("Invalid command")
//                }
                println(session.getStatus().getHint())
            }
        }
    }
    scanner.close()
}


