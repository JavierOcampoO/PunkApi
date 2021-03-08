package com.test.punkapi.data.api.requests

import com.test.punkapi.data.api.RequestBuilder
import com.test.punkapi.data.model.Beer
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.QueryMap

interface BeersRequests {

    @GET("v2/beers")
    @Headers(RequestBuilder.acceptJson)
    fun getBeers(@QueryMap map: HashMap<String, Int>): Call<List<Beer>>

}