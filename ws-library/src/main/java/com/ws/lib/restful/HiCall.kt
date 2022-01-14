package com.ws.lib.restful

import java.io.IOException

interface HiCall<T>{

    @Throws(IOException::class)
    fun execute():HiResponse<T>

    fun enqueue(callback: HiCallback<T>)


    interface Factory{

        fun newCall(request:HiRequest):HiCall<*>
    }
}