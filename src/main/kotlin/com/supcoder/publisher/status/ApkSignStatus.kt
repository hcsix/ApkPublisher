package com.supcoder.publisher.status

import com.supcoder.publisher.task.SignTask


/**
 * ApkSignStatus
 *
 * @author lee
 * @date 2024/10/31
 */
class ApkSignStatus : Status() {

    private var keystorePath: String = ""
    private var keyStorePwd: String = ""
    private var keyAlias: String = ""
    private var keyPwd: String = ""

    override fun parseCommand(command: String): Boolean {
        if (keystorePath.isEmpty()) {
            keystorePath = command.trim()
            return true
        }
        if (keyStorePwd.isEmpty()) {
            keyStorePwd = command.trim()
            return true
        }
        if (keyAlias.isEmpty()) {
            keyAlias = command.trim()
            return true
        }
        if (keyPwd.isEmpty()) {
            keyPwd = command.trim()
            SignTask.sign(getSession().config.getUnSignedApkPath(), keystorePath, keyStorePwd, keyAlias, keyPwd)
            return true
        }
        return true
    }

    override fun getHint(): String {
        when {
            keystorePath.isEmpty() -> {
                return "请输入keystore路径"
            }

            keyStorePwd.isEmpty() -> {
                return "请输入keystore密码"
            }
            keyAlias.isEmpty() -> {
                return "请输入keyAlias"
            }
            keyPwd.isEmpty() -> {
                return "请输入key密码"
            }
            else -> {
                return ""
            }
        }
    }


    override fun next(): Status {
        return FinishStatus()
    }


}