package com.example.simplebeerapp.ui.home.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.simplebeerapp.data.data_source.BeerDB
import com.example.simplebeerapp.data.model.Beer
import com.example.simplebeerapp.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    companion object {
        var BEER_ID = "beerId"
    }

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private var beerId = 0
    private lateinit var beerInstance: Beer

    private val viewModel: DetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            beerId = it.getInt(BEER_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.init(BeerDB.getDatabase(requireContext()))
        viewModel.apply {
            init(BeerDB.getDatabase(requireContext()))
            getBeerById(beerId)
        }
        beerInstance = viewModel.beer.value!!
        binding.apply {
            imageView.setImageResource(beerInstance.imageResource)
            textView.text = beerInstance.name
            textView2.text = beerInstance.info.toString()
        }
    }
}