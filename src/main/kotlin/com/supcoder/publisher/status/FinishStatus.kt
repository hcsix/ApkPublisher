package com.supcoder.publisher.status

/**
 * FinishStatus
 *
 * @author lee
 * @date 2024/10/31
 */
class FinishStatus : Status() {

    override fun parseCommand(command: String): Boolean {
        return true
    }

    override fun getHint(): String {
        return "任务完成，请按回车键退出"
    }


    override fun next(): Status {
        return FinishStatus()
    }

}