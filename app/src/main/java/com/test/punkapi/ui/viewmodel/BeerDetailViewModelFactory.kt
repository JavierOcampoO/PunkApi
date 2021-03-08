package com.test.punkapi.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.test.punkapi.data.model.Beer

@Suppress("UNCHECKED_CAST")
class BeerDetailViewModelFactory(private val beer: Beer): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BeerDetailViewModel::class.java)) {
            return BeerDetailViewModel(beer) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}