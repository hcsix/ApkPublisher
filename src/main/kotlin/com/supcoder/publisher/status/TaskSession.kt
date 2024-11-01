package com.supcoder.publisher.status

import com.supcoder.publisher.model.Config

/**
 * TaskSession
 *
 * @author lee
 * @date 2024/10/31
 */
object TaskSession {

    private var status: Status = ConfigPathStatus()

    val config = Config("", "", "", hashMapOf())

    fun parseCommand(command: String): Boolean {
        return status.parseCommand(command)
    }

    fun getStatus(): Status {
        return status
    }

    fun changeStatus(status: Status) {
        this.status = status
    }


}