package com.example.simplebeerapp.ui.favourite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.simplebeerapp.BeerAdapter
import com.example.simplebeerapp.BeerAdapterClockListener
import com.example.simplebeerapp.data.data_source.BeerDB
import com.example.simplebeerapp.databinding.FragmentFavBinding
import com.example.simplebeerapp.ui.home.HomeFragmentDirections

class FavouriteFragment : Fragment(), BeerAdapterClockListener {

    private var _binding: FragmentFavBinding? = null
    private val binding get() = _binding!!

    private val favViewModel: FavouriteViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        favViewModel.init(BeerDB.getDatabase(requireContext()))
        favViewModel.getFavBeer()

        val adapter = BeerAdapter(this)
        binding.recyclerBeerList.adapter = adapter
        binding.recyclerBeerList.layoutManager = LinearLayoutManager(requireContext())

        favViewModel.beerList.observe(viewLifecycleOwner) { beerList ->
            adapter.configureList(beerList)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun checkBoxUpdate(id: Int) {
        favViewModel.updateDB(id)
    }

    override fun navigateTo(id: Int) {
        val action = HomeFragmentDirections.actionNavigationHomeToDetailFragment(id)
        binding.root.findNavController()
            .navigate(action)

    }
}