package com.ws.lib.restful

/**
 * callbak 回调
 */
interface HiCallback<T> {
    fun onSuccess(response: HiResponse<T>)
    fun onFailed(throwable: Throwable){}
}