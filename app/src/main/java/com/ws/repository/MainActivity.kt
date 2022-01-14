package com.ws.repository

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ws.lib.executor.HiExecutor
import com.ws.lib.util.HiDataBus

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}