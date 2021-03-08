package com.test.punkapi.utils.handlers

data class Resource<out T> (val status: ResourseStatus, val data: T?, val message: String?){

    companion object {
        fun <T> success(data: T): Resource<T> = Resource(status = ResourseStatus.SUCCESS, data = data, message = null)

        fun <T> error(data: T?, message: String): Resource<T> = Resource(status = ResourseStatus.ERROR, data = data, message = message)

        fun <T> loading(data: T?): Resource<T> = Resource(status = ResourseStatus.LOADING, data = data, message = null)
    }

}