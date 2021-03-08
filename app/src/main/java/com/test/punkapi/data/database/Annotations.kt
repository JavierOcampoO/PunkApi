package com.test.punkapi.data.database

@Target(AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class DatabaseEnviroment(val name: String)