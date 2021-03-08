package com.test.punkapi.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.test.punkapi.data.database.AppDatabase
import com.test.punkapi.data.model.Beer
import com.test.punkapi.data.repository.BeersRepository

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(private val database: Beer.Dao): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(BeersRepository(database)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}