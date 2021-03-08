package com.test.punkapi.ui.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.punkapi.data.database.AppDatabase
import com.test.punkapi.data.model.Beer
import com.test.punkapi.databinding.ActivityMainBinding
import com.test.punkapi.ui.adapters.BeersAdapter
import com.test.punkapi.ui.viewmodel.MainViewModel
import com.test.punkapi.ui.viewmodel.MainViewModelFactory
import com.test.punkapi.utils.handlers.OnItemClickListener
import com.test.punkapi.utils.handlers.ResourseStatus


class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }


}