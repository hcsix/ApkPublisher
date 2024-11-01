package com.supcoder.publisher.status

import com.supcoder.publisher.command.Command

/**
 * ConfigPropertiesStatus
 *
 * @author lee
 * @date 2024/10/30
 */
class ConfigPropertiesStatus : Status() {

    private var propertiesName = ""
    private val propertiesMap = hashMapOf<String, String>()

    override fun parseCommand(command: String): Boolean {
        val commandTemp = command.trim()
        if (propertiesName.isEmpty()) {
            if (commandTemp.isNotBlank()) {
                propertiesName = commandTemp
                getSession().config.propertiesName = propertiesName
                return true
            } else {
                return true
            }
        }
        if (command.equals(Command.DONE, ignoreCase = true)){
            getSession().changeStatus(next())
            return true
        }
        val properties = commandTemp.split(" ")
        if (properties.size == 2) {
            if (properties[0].isNotBlank() && properties[1].isNotBlank()) {
                propertiesMap[properties[0]] = properties[1]
                getSession().config.properties[properties[0]] = properties[1]
                return true
            } else {
                return false
            }
        } else {
            return false
        }
    }

    override fun getHint(): String {
        return when {
            propertiesName.isNotBlank() -> {
                if (propertiesMap.isEmpty()){
                    "请输入Properties文件内容(key value)，如：\n" + "key1 value1"
                }else{
                    "请继续输入Properties文件内容(key value)，如：\n" + "key3 value3,或输入${Command.DONE}完成输入"
                }
            }
            else -> "请输入Properties文件名称:"
        }
    }

    override fun toString(): String {
        return "ConfigPropertiesStatus"
    }

    override fun next(): Status {
        return ApkBuildStatus()
    }
}