package com.test.punkapi.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.punkapi.R
import com.test.punkapi.data.database.AppDatabase
import com.test.punkapi.data.model.Beer
import com.test.punkapi.databinding.FragmentBeerDetailBinding
import com.test.punkapi.databinding.FragmentMainBinding
import com.test.punkapi.ui.adapters.BeersAdapter
import com.test.punkapi.ui.adapters.FoodAdapter
import com.test.punkapi.ui.viewmodel.BeerDetailViewModel
import com.test.punkapi.ui.viewmodel.BeerDetailViewModelFactory
import com.test.punkapi.ui.viewmodel.MainViewModel
import com.test.punkapi.ui.viewmodel.MainViewModelFactory
import com.test.punkapi.utils.extenders.glide_setUrlImage
import com.test.punkapi.utils.handlers.OnItemClickListener
import java.text.SimpleDateFormat


class BeerDetailFragment : Fragment() {

    private lateinit var beerViewModel: BeerDetailViewModel

    private var _binding: FragmentBeerDetailBinding? = null

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            val beer = it.getSerializable(EXTRA_BEER) as Beer

            beerViewModel = ViewModelProviders.of(this, BeerDetailViewModelFactory(beer))
                .get(BeerDetailViewModel::class.java)

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBeerDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
    }

    private fun initViewModel() {

        beerViewModel.beer.observe(requireActivity()){
            initUI(it)
        }

    }

    private fun initUI(beer: Beer) {
        binding.txtTitle.text = beer.name
        binding.txtTagline.text = beer.tagline
        beer.imageUrl?.let { binding.imgBeer.glide_setUrlImage(it) }

        binding.txtDescription.text = beer.description
        binding.txtFirstBrewed.text = beer.firstBrewed


        beer.foodPairing?.let {
            val adapter = FoodAdapter(it.toMutableList())
            val layoutManager = LinearLayoutManager(requireContext())
            binding.listFoodPairing.layoutManager = layoutManager
            binding.listFoodPairing.adapter = adapter
        }
    }

    companion object {

        const val EXTRA_BEER = "beer"

        @JvmStatic
        fun newInstance(beer: Beer) =
            BeerDetailFragment().apply {
                arguments = bundleOf( EXTRA_BEER to beer )
            }

    }
}