package com.example.simplebeerapp.ui.snacks

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.simplebeerapp.data.data_source.BeerDB
import com.example.simplebeerapp.databinding.FragmentSnacksBinding

class SnacksFragment : Fragment(), SnackClickListener {

    private var _binding: FragmentSnacksBinding? = null
    private val binding get() = _binding!!

    private val snacksViewModel: SnacksViewModel by viewModels()

    private val adapter by lazy { SnackAdapter(this) }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSnacksBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        snacksViewModel.apply {
            init(BeerDB.getDatabase(requireContext()))
            getSnacksFromApi()
        }

        binding.snacksRV.adapter = adapter
        binding.snacksRV.layoutManager = GridLayoutManager(requireContext(), 2)

        snacksViewModel.snacksLiveData.observe(viewLifecycleOwner) { snacksList ->
            if (snacksList == null) {
                Toast.makeText(
                    requireContext(),
                    "Unsuccessful network call!",
                    Toast.LENGTH_SHORT
                ).show()
                return@observe
            }
            if (snacksList.data.isEmpty()) {
                Toast.makeText(
                    requireContext(),
                    "List is empty!",
                    Toast.LENGTH_SHORT
                ).show()
                Log.d("Fragment", "List is empty!")
                return@observe
            }
            adapter.differ.submitList(snacksList.data)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}