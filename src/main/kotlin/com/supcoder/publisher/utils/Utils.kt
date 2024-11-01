package com.supcoder.publisher.utils

import java.io.File

/**
 * Utils
 *
 * @author lee
 * @date 2024/10/31
 */
object Utils {

    /**
     * 获取当前工作目录
     * @return String
     */
    fun getCurrentDir(): String {
        // 获取当前工作目录
        val currentDir = System.getProperty("user.dir")
        // 打印当前工作目录
        println("当前工作目录: $currentDir")
        if (currentDir.isNullOrEmpty()) {
            // 另一种方法，使用 File 类
            val currentDirFile = File(".")
            try {
                val absolutePath = currentDirFile.canonicalPath
                println("当前工作目录 (使用 File): $absolutePath")
                return absolutePath
            } catch (e: Exception) {
                println(e)
                e.printStackTrace()
            }
        }
        return currentDir
    }


    /**
     * 查找指定路径下的所有 .apk 文件路径
     *
     * @param path 要查找的文件路径
     * @return 找到的 .apk 文件路径的字符串数组
     */
    fun findApkFiles(path: String): Array<String> {
        val file = File(path)
        return if (file.exists()) {
            if (file.isFile && file.extension.equals("apk", ignoreCase = true)) {
                arrayOf(path)
            } else if (file.isDirectory) {
                file.listFiles { it -> it.extension.equals("apk", ignoreCase = true) }?.map { it.path }?.toTypedArray()
                    ?: emptyArray()
            } else {
                emptyArray()
            }
        } else {
            emptyArray()
        }
    }


}