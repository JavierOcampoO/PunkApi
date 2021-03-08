package com.test.punkapi.data.api

@Target(AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class Enviroment(val BaseUrl: String)

@Target(AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class OkHttpParams(val withLogger: Boolean = true, val timeout: Long = 60)