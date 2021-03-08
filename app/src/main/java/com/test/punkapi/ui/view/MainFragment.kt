package com.test.punkapi.ui.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.punkapi.R
import com.test.punkapi.data.database.AppDatabase
import com.test.punkapi.data.model.Beer
import com.test.punkapi.databinding.FragmentMainBinding
import com.test.punkapi.ui.adapters.BeersAdapter
import com.test.punkapi.ui.viewmodel.MainViewModel
import com.test.punkapi.ui.viewmodel.MainViewModelFactory
import com.test.punkapi.utils.handlers.OnItemClickListener
import com.test.punkapi.utils.handlers.ResourseStatus


class MainFragment : Fragment() {

    private lateinit var mainViewModel: MainViewModel

    private var _binding: FragmentMainBinding? = null

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainViewModel = ViewModelProviders.of(this, MainViewModelFactory(AppDatabase(requireContext()).beers()))
            .get(MainViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()
        initViewModel()
    }

    private fun initViewModel() {

        mainViewModel.beersList.observe(requireActivity()){
            when(it.status){
                ResourseStatus.SUCCESS -> {
                    binding.swiperefreshBeers.isRefreshing = false
                    it.data?.let { list -> addItemsToList(list) }
                }
                ResourseStatus.ERROR -> {
                    binding.swiperefreshBeers.isRefreshing = false
                }
                ResourseStatus.LOADING -> {
                    binding.swiperefreshBeers.isRefreshing = true
                }
            }
        }

        getBeers()

    }

    private fun getBeers(clearAll: Boolean = false){
        if(clearAll){
            val adapter = binding.recyclerBeers.adapter as BeersAdapter
            adapter.removeItems()
        }
        mainViewModel.getBeers(clearAll)
    }

    private fun addItemsToList(data: List<Beer>) {
        if(data.isNotEmpty()){
            val adapter = binding.recyclerBeers.adapter as BeersAdapter
            adapter.addItems(data)
        }
    }

    private fun initUi(){
        val adapter = BeersAdapter(ArrayList(), object : OnItemClickListener<Beer> {
            override fun onItemClick(position: Int, item: Beer) {
                val bundle = bundleOf( BeerDetailFragment.EXTRA_BEER to item )
                Navigation.findNavController(binding.root).navigate(R.id.action_mainFragment_to_beerDetailFragment, bundle)
            }
        })
        val layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerBeers.layoutManager = layoutManager
        binding.recyclerBeers.adapter = adapter

        binding.recyclerBeers.addOnScrollListener( object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val visibleItemCount: Int = layoutManager.childCount
                val totalItemCount: Int = layoutManager.itemCount
                val firstVisibleItemPosition: Int = layoutManager.findFirstVisibleItemPosition()

                if(!binding.swiperefreshBeers.isRefreshing){
                    if (firstVisibleItemPosition >= 0 && visibleItemCount + firstVisibleItemPosition >= totalItemCount) {
                        getBeers()
                    }
                }
            }
        })

        binding.swiperefreshBeers.setOnRefreshListener { getBeers(clearAll = true) }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        fun newInstance() = MainFragment().apply {

        }

    }
}