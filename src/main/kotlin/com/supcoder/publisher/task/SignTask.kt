package com.supcoder.publisher.task

import com.android.apksigner.ApkSignerTool
import com.supcoder.publisher.Constants.SUFFIX_APK_SIGNED
import com.supcoder.publisher.Constants.SUFFIX_APK_UNSIGNED

/**
 * SignTask
 *
 * @author lee
 * @date 2024/10/30
 */
object SignTask {

    /**
     * 重新签名
     * java -jar /path/to/apksigner.jar sign --ks my-release-key.keystore --ks-key-alias my-key-alias --out signed-app.apk unsigned-app.apk
     * /path/to/apksigner.jar：apksigner.jar 文件的路径。
     * my-release-key.keystore：你的 keystore 文件。
     * my-key-alias：keystore 的别名。
     * unsigned-app.apk：未签名的 APK 文件。
     * signed-app.apk：签名后的 APK 文件。
     *
     * @param apkFilePath 未签名的apk路径
     * @param keystorePath keystore路径
     * @param keyAlias keystore 的别名
     */
    fun sign(apkFilePath: String, keystorePath: String, keyStorePwd:String,keyAlias: String,keyPwd:String) {
        println("apkFilePath=$apkFilePath,keystorePath=$keystorePath,keyAlias=$keyAlias,keyStorePwd=$keyStorePwd,keyPwd=$keyPwd")
//        val signVersions = listOf(SignType.V1, SignType.V2)
//        val signVersionParams = SignType.ALL_SIGN_TYPES.flatMap {
//            arrayListOf("--v${it.type}-signing-enabled", if (signVersions.contains(it)) "true" else "false")
//        }.toTypedArray()

        val outputDir = if (apkFilePath.endsWith(SUFFIX_APK_UNSIGNED)) {
            apkFilePath.replace(SUFFIX_APK_UNSIGNED, SUFFIX_APK_SIGNED)
        } else {
            "${apkFilePath.substringBeforeLast(".")}$SUFFIX_APK_SIGNED"
        }
        // 构建参数
        val args = arrayOf(
            "sign",
            "--ks",// signer 的私钥和证书链包含在给定的基于 Java 的密钥库文件中
            keystorePath,
            "--ks-key-alias",// 表示 signer 在密钥库中的私钥和证书数据的别名的名称
            keyAlias,
            "--key-pass",// signer 私钥的密码。
            "pass:$keyPwd",
            "--ks-pass",// 包含 signer 私钥和证书的密钥库的密码。
            "pass:$keyStorePwd",
//            *signVersionParams,
            "--out",
            outputDir,
            apkFilePath
        )
        println(args.joinToString(" , "))
        try {
            ApkSignerTool.main(args)
            println("APK 签名成功")
        } catch (e: Exception) {
            e.printStackTrace()
            println("APK 签名失败")
        }
    }


    fun verify(apkFilePath: String) {
        // 构建参数
        val args = arrayOf("verify", "--verbose", apkFilePath)
        try {
            ApkSignerTool.main(args)
            println("APK 签名验证结果输出结束，请检查")
        } catch (e: Exception) {
            e.printStackTrace()
            println("APK 签名验证失败")
        }
    }
}