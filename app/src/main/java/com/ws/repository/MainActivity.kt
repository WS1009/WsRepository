package com.ws.repository

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import com.ws.lib.cache.HiStorage
import com.ws.lib.executor.HiExecutor
import com.ws.lib.util.DPIUtil
import com.ws.ui.scroll.MatrixImageView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        HiExecutor.execute(runnable = Runnable {
            HiStorage.saveCache("aaa", "body")
        })

        HiExecutor.execute(runnable = Runnable {
            val cache = HiStorage.getCache<String>("aaa")
            println("cache: $cache")
        })

        val img3: com.ws.ui.scroll.MatrixImageView = findViewById(R.id.matrixImg)
        img3.layoutParams = LinearLayout.LayoutParams(
            DPIUtil.getWidthByDesignValue750(this, 100),
            DPIUtil.getWidthByDesignValue750(this, 100)
        )
    }
}