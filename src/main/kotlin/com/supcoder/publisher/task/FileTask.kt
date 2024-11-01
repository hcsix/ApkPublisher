package com.supcoder.publisher.task

import java.io.FileOutputStream
import java.io.IOException
import java.util.*

/**
 * FileTask
 *
 * @author lee
 * @date 2024/10/30
 */
object FileTask {
    fun createAndWritePropertiesFile(
        filePath: String,
        map: Map<String, String>
    ) {
        if (map.isEmpty()) {
            return
        }

        // 创建 Properties 对象
        val properties = Properties()
        // 设置属性值
        map.forEach { (key, value) ->
            properties[key] = value
        }
        // 写入 properties 文件
        try {
            val outputStream = FileOutputStream(filePath)
            properties.store(outputStream, "Extension")
            outputStream.close()
            println("Properties file written successfully to $filePath")
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

}