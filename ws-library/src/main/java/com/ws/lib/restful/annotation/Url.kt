package com.ws.lib.restful.annotation

/**
 * @BaseUrl("https://api.devio.org/as/")
 *fun test(@Url("province") int provinceId)
 */
@Target(AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
annotation class Url()