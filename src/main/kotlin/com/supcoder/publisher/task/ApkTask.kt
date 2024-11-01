package com.supcoder.publisher.task

import brut.apktool.Main
import com.supcoder.publisher.Constants.PATH_UNZIP
import com.supcoder.publisher.Constants.SUFFIX_APK_UNSIGNED
import com.supcoder.publisher.utils.Utils.getCurrentDir
import java.io.File

/**
 * ApkUtils
 *
 * @author lee
 * @date 2024/10/30
 */
object ApkTask {


    fun decodeApk(apkFilePath: String, path: String) {
        val outputDir = "$path/$PATH_UNZIP/"
        // 检查输出目录是否存在，如果不存在则创建
        File(outputDir).mkdirs()
        // 构建参数
        val args = arrayOf("d", "-f", "-r", apkFilePath, "-o", outputDir)
        try {
            Main.main(args)
            println("APK 反编译成功")
        } catch (e: Exception) {
            e.printStackTrace()
            println("APK 反编译失败")
        }
    }


    fun encodeApk(unzipPath: String = getCurrentDir(),rootPath:String,apkName:String):String {
        val outputDir = "$rootPath/$apkName$SUFFIX_APK_UNSIGNED"
        // 检查输出目录是否存在，如果不存在则创建
        File(outputDir).mkdirs()
        // 构建参数
        val args = arrayOf("b", unzipPath, "-o", outputDir, "--use-aapt2")
        try {
            Main.main(args)
            println("APK 重新打包成功")
        } catch (e: Exception) {
            e.printStackTrace()
            println("APK 重新打包失败")
        }
        return outputDir
    }
}