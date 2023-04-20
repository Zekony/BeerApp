package com.example.simplebeerapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.simplebeerapp.R
import com.example.simplebeerapp.data.entities.Beer.Companion.beers
import com.example.simplebeerapp.databinding.FragmentHomeBinding
import com.example.simplebeerapp.ui.core.BeerAdapterClockListener
import com.example.simplebeerapp.ui.home.models.FilterBeerType
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(), BeerAdapterClockListener {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val homeViewModel: HomeViewModel by viewModels()

    private val adapter by lazy { BeerAdapter(this) }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel.getAllBeer()

        binding.recyclerBeerList.adapter = adapter
        binding.recyclerBeerList.layoutManager = LinearLayoutManager(requireContext())

        binding.radioGroup.setOnCheckedChangeListener { _, id ->
            when (id) {
                R.id.rbNoFilter -> homeViewModel.filterBeers(FilterBeerType.NONE)
                R.id.rb_DarkBeer -> homeViewModel.filterBeers(FilterBeerType.DARK)
                R.id.rb_LightBeer -> homeViewModel.filterBeers(FilterBeerType.LIGHT)
                R.id.rb_IpaBeer -> homeViewModel.filterBeers(FilterBeerType.IPA)
            }
        }
        homeViewModel.beerList.observe(viewLifecycleOwner) { beerList ->
            adapter.differ.submitList(beerList)

            if (beerList.isEmpty()) {
                insertDataToDatabase()
            }
        }
    }

    private fun insertDataToDatabase() {
        for (i in beers) {
            homeViewModel.addBeerCor(i)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun checkBoxUpdate(id: Int, isFavorite: Boolean) {
        homeViewModel.updateDB(id, isFavorite)
    }

    override fun navigateTo(id: Int, beerName: String) {
        val action = HomeFragmentDirections.actionNavigationHomeToDetailFragment(id, beerName)
        binding.root.findNavController().navigate(action)
    }


}