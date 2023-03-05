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
import com.example.simplebeerapp.R
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
                R.id.rb1 -> homeViewModel.filterBeers(1)
                R.id.rb2 -> homeViewModel.filterBeers(2)
                R.id.rb3 -> homeViewModel.filterBeers(3)
            }
        }

        homeViewModel.beerList.observe(viewLifecycleOwner) { beerList ->
            adapter.configureList(beerList)
            if (beerList.isEmpty()) {
                insertDataToDatabase()
            }
        }

        return binding.root
    }

    fun insertDataToDatabase() {
        for (i in beers) {
            homeViewModel.addBeerCor(i)
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