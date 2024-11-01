package com.supcoder.publisher.status

import com.supcoder.publisher.Constants.PATH_UNZIP
import com.supcoder.publisher.task.ApkTask
import com.supcoder.publisher.task.FileTask

/**
 * ApkBuildStatus
 *
 * @author lee
 * @date 2024/10/31
 */
class ApkBuildStatus : Status() {

    override fun parseCommand(command: String): Boolean {
       val result = kotlin.runCatching {
            getSession().config.let {
                ApkTask.decodeApk(it.apkPath, it.path)
                FileTask.createAndWritePropertiesFile(
                    "${it.path}/$PATH_UNZIP/assets/${it.propertiesName ?: "ext"}.properties",
                    it.properties
                )
                ApkTask.encodeApk("${it.path}/$PATH_UNZIP/", it.path, it.getApkName())
                getSession().changeStatus(next())
            }
        }
        if (result.isFailure) {
            println("编译失败")
            return false
        }
        return true
    }

    override fun getHint(): String {
        return "回车开始编译"
    }


    override fun next(): Status {
        return ApkSignStatus()
    }

}