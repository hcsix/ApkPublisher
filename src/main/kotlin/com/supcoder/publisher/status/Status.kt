package com.supcoder.publisher.status

import com.supcoder.publisher.command.Command

/**
 * Status
 *
 * @author lee
 * @date 2024/10/30
 */
open class Status {

    var isDone = false
    open fun getHint(): String {
        return "请输入${Command.HELP}查看帮助"
    }

    open fun parseCommand(command: String): Boolean {
        return false
    }

    open fun next(): Status {
        return this
    }

    open fun getSession() = TaskSession

}