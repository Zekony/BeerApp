package com.example.simplebeerapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.simplebeerapp.BeerAdapter
import com.example.simplebeerapp.BeerAdapterClockListener
import com.example.simplebeerapp.data.data_source.BeerDB
import com.example.simplebeerapp.data.model.Beer
import com.example.simplebeerapp.data.model.beers
import com.example.simplebeerapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment(), BeerAdapterClockListener {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        homeViewModel.init(BeerDB.getDatabase(requireContext()))
/*        insertDataToDatabase()*/
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        homeViewModel.getAllBeer()

        val adapter = BeerAdapter(this)
        binding.recyclerBeerList.adapter = adapter
        binding.recyclerBeerList.layoutManager = LinearLayoutManager(requireContext())

        var chosenBeerType: Int = 0
        binding.radioGroup.setOnCheckedChangeListener { _, id ->
            when (id) {
                0 -> homeViewModel.beerList.value?.let { homeViewModel.filterBeers(it, 1) }
                1 -> homeViewModel.beerList.value?.let { homeViewModel.filterBeers(it, 2) }
                2 -> homeViewModel.beerList.value?.let { homeViewModel.filterBeers(it, 3) }
            }
        }

        homeViewModel.beerList.observe(viewLifecycleOwner) {
            beerList -> adapter.getBeerList(beerList)
        }

        return binding.root
    }

    fun insertDataToDatabase() {
        if (homeViewModel.beerList.value == null) {
            for (i in beers) {
                homeViewModel.addBeerCor(i)
            }
        } else {

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun checkBoxUpdate(id: Int) {
        homeViewModel.updateDB(id)
    }
}