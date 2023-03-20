package com.example.simplebeerapp.ui.home.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.simplebeerapp.data.data_source.BeerDB
import com.example.simplebeerapp.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    private val safeArgs: DetailFragmentArgs by navArgs()

    private var beerId = 0
    private var beerName = ""

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DetailViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        beerId = safeArgs.id

        viewModel.apply {
            init(BeerDB.getDatabase(requireContext()))
            getBeerById(beerId)
        }

        viewModel.beer.observe(viewLifecycleOwner) {
            binding.apply {
                imageView.setImageResource(it.imageResource)
                textView.text = it.name
                textView2.text = getString(it.info)
                beerName = it.name
            }
        }
    }
}