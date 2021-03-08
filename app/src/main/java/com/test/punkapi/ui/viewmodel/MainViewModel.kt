package com.test.punkapi.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.punkapi.data.model.Beer
import com.test.punkapi.data.repository.BeersRepository
import com.test.punkapi.utils.handlers.Resource
import com.test.punkapi.utils.handlers.ResourseStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException
import java.lang.Exception
import java.lang.RuntimeException

class MainViewModel(val beersRepository: BeersRepository): ViewModel() {

    companion object {
      private const val PER_PAGE = 20
    }

    private var actualPage = 0

    private val _beersList: MutableLiveData<Resource<List<Beer>>> = MutableLiveData()

    val beersList: LiveData<Resource<List<Beer>>> = _beersList

    fun getBeers(clearCount: Boolean = false ) {
        _beersList.value = Resource.loading(null)

        if(clearCount) {
            actualPage = 0
        }
        actualPage += 1

        viewModelScope.launch {

            withContext(Dispatchers.IO){
                try {

                    val beersRequest = beersRepository.getBeers(HashMap<String, Int>().apply{
                        this["page"] = actualPage
                        this["per_page"] = PER_PAGE
                    })


                    if (beersRequest.code() == 200) {
                        _beersList.postValue(Resource.success(beersRequest.body()!!))

                       insertDatabaseBeers(beersRequest.body()!!)
                    } else {
                       getDatabaseBeers()
                    }

                } catch (ex: IOException) {
                    getDatabaseBeers()
                } catch (ex: RuntimeException) {

                    getDatabaseBeers()
                }
            }

        }
    }

    private fun insertDatabaseBeers(beers: List<Beer>) =
        viewModelScope.launch { beersRepository.insertBeersDB(beers) }

    private fun getDatabaseBeers() {
        viewModelScope.launch {
            try {
                val beers = beersRepository.getBeersDB()
                _beersList.postValue(Resource.success(beers))
            } catch (ex: Exception) {
                _beersList.value = Resource.error(null, "Ocurrio un error!")
            }
        }
    }
}




