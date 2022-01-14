package com.ws.repository

import android.app.Application
import com.google.gson.Gson
import com.ws.lib.log.HiConsolePrinter
import com.ws.lib.log.HiFilePrinter
import com.ws.lib.log.HiLogConfig
import com.ws.lib.log.HiLogManager
import com.ws.lib.util.ActivityManager

open class HiBaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initLog()
    }


    private fun initLog() {
        HiLogManager.init(object : HiLogConfig() {
            override fun injectJsonParser(): JsonParser {
                return JsonParser { src: Any? -> Gson().toJson(src) }
            }

            override fun includeThread(): Boolean {
                return true
            }
        }, HiConsolePrinter(), HiFilePrinter.getInstance(cacheDir.absolutePath, 0))

        ActivityManager.instance.init(this)
    }
}