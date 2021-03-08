package com.test.punkapi.data.repository

import com.test.punkapi.data.api.RequestBuilder
import com.test.punkapi.data.api.requests.BeersRequests
import com.test.punkapi.data.database.AppDatabase
import com.test.punkapi.data.model.Beer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class BeersRepository(private val beerDao: Beer.Dao) {

    fun getBeers(query: HashMap<String, Int>) =  RequestBuilder(BeersRequests::class.java).getBeers(query).execute()

    suspend fun getBeersDB() = withContext(Dispatchers.IO){ beerDao.all() }

    suspend fun insertBeersDB(list: List<Beer>) = withContext(Dispatchers.IO){ beerDao.insert(list) }
}