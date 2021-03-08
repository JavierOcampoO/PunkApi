package com.test.punkapi.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.punkapi.data.model.Beer

class BeerDetailViewModel(beer: Beer): ViewModel() {

    private val _beer: MutableLiveData<Beer> = MutableLiveData(beer)

    val beer: LiveData<Beer>
        get() = _beer


}