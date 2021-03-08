package com.test.punkapi.data.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Enviroment(BaseUrl = "https://api.punkapi.com/")
class RequestBuilder {

    public fun getEnviromentBaseUrl(): String =
        this::class.java.getAnnotation(Enviroment::class.java)!!.BaseUrl

    private val retrofit: Retrofit by lazy { initializeRetrofit() }

    fun initializeRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(getEnviromentBaseUrl())
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun <T> retrofitCreate(clase: Class<T>): T {
        return retrofit.create(clase)
    }

    @OkHttpParams(withLogger = true, timeout = 120)
    companion object {

        private fun getParamWithLogger(): Boolean = this::class.java.getAnnotation(OkHttpParams::class.java)!!.withLogger
        private fun getParamTimeOut(): Long = this::class.java.getAnnotation(OkHttpParams::class.java)!!.timeout

        public fun getEnviromentBaseUrl(): String = RequestBuilder::class.java.getAnnotation(
            Enviroment::class.java)!!.BaseUrl

        const val acceptJson: String = "Accept: application/json"

        private var instance: RequestBuilder? = null

        fun getInstance(): RequestBuilder {
            if (instance == null) {
                instance = RequestBuilder()
            }
            return instance as RequestBuilder
        }

        val client: OkHttpClient
            get() {
                val client = OkHttpClient.Builder()

                if(getParamWithLogger()){
                    val interceptor = HttpLoggingInterceptor()
                    interceptor.level = HttpLoggingInterceptor.Level.BODY
                    client.interceptors().add(interceptor)
                }


                client.connectTimeout(getParamTimeOut(), TimeUnit.SECONDS)
                    .readTimeout(getParamTimeOut(), TimeUnit.SECONDS)
                    .writeTimeout(getParamTimeOut(), TimeUnit.SECONDS)

                return client.build()
            }

        fun <T> with(clase: Class<T>): T {
            return getInstance().retrofitCreate(clase)
        }

        operator fun <T> invoke(clase: Class<T>): T{
            return getInstance().retrofitCreate(clase)
        }

    }

}