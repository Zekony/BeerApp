package com.example.simplebeerapp.ui.favourite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.simplebeerapp.R
import com.example.simplebeerapp.data.data_source.BeerDB
import com.example.simplebeerapp.databinding.FragmentFavBinding
import com.example.simplebeerapp.ui.home.BeerAdapter
import com.example.simplebeerapp.ui.home.BeerAdapterClockListener

class FavouriteFragment : Fragment(), BeerAdapterClockListener {

    private var _binding: FragmentFavBinding? = null
    private val binding get() = _binding!!

    private val favViewModel: FavouriteViewModel by viewModels()

    private val adapter by lazy { BeerAdapter(this) }

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

        favViewModel.init(BeerDB.getBeerDatabase(requireContext()))
        favViewModel.getFavBeer()

        binding.recyclerBeerList.adapter = adapter
        binding.recyclerBeerList.layoutManager = LinearLayoutManager(requireContext())

        favViewModel.beerList.observe(viewLifecycleOwner) { beerList ->
            adapter.differ.submitList(beerList)
            setTextView()
        }
    }

    private fun setTextView() {
        if (favViewModel.beerList.value?.isEmpty() == true) {
            binding.text.text =
                getString(R.string.noFavBeer)
        }
        if (favViewModel.beerList.value?.isNotEmpty() == true)
            binding.text.text = ""
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun checkBoxUpdate(id: Int, isFavorite: Boolean) {
        favViewModel.updateDB(id, isFavorite)
    }

    override fun navigateTo(id: Int, beerName: String) {
        val action = FavouriteFragmentDirections.actionNavigationDashboardToDetailFragment(id, beerName)
        binding.root.findNavController()
            .navigate(action)
    }
}