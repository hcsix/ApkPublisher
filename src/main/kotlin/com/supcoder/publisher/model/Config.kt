package com.supcoder.publisher.model

import com.supcoder.publisher.Constants.SUFFIX_APK_UNSIGNED

/**
 * Config
 *
 * @author lee
 * @date 2024/10/31
 */
data class Config(
    var apkPath: String,
    var path: String,
    var propertiesName: String?,
    var properties: HashMap<String, String>
){
    fun getApkName(): String{
        return apkPath.substringAfterLast("/").substringBeforeLast(".")
    }

    fun getUnSignedApkPath(): String{
        return "$path/${getApkName()}$SUFFIX_APK_UNSIGNED"
    }
}
