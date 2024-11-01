package com.supcoder.publisher.status

import com.supcoder.publisher.utils.Utils

/**
 * ConfigPathStatus
 *
 * @author lee
 * @date 2024/10/30
 */
class ConfigPathStatus : Status() {

    private var apkPath = ""
    private var rootPath = ""
    private var apkPathArray: Array<String> = arrayOf()

    override fun getHint(): String {
        return "请输入apk文件路径(回车默认为当前目录):"
    }

    override fun parseCommand(command: String): Boolean {
        if (apkPathArray.isEmpty()) {
            val path = command.ifBlank {
                Utils.getCurrentDir()
            }
            val apkPathArray = Utils.findApkFiles(path)
            if (apkPathArray.size == 1) {
                apkPath = apkPathArray[0]
                rootPath = apkPath.substringBeforeLast("/")
                saveConfig()
            } else if (apkPathArray.size > 1) {
                this.apkPathArray = apkPathArray
                println("请选择apk文件:")
                apkPathArray.forEachIndexed { index, s ->
                    println("${index + 1}: $s")
                }
                return true
            } else {
                println("$path 路径下没有找到apk文件")
                return false
            }
        } else {
            val index = command.toIntOrNull() ?: return false
            if (index in 1..apkPathArray.size) {
                apkPath = apkPathArray[index - 1]
                rootPath = apkPath.substringBeforeLast("/")
                saveConfig()
                return true
            } else {
                println("请输入正确的序号")
            }
        }
        return true
    }


    private fun saveConfig() {
        getSession().config.apkPath = apkPath
        getSession().config.path = rootPath
        println("你选择的apk文件为: $apkPath")
        isDone = true
        getSession().changeStatus(next())
    }

    override fun next(): Status {
        return ConfigPropertiesStatus()
    }

    override fun toString(): String {
        return "ConfigPathStatus"
    }


}