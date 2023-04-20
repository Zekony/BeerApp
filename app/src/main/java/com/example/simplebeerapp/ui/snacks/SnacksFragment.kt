package com.example.simplebeerapp.ui.snacks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.simplebeerapp.databinding.FragmentSnacksBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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

        snacksViewModel.getSnacksFromApi()

        lifecycleScope.launchWhenCreated {
            binding.progressBar.isVisible = true
        }
        binding.apply {
            snacksRV.adapter = adapter
            snacksRV.layoutManager = GridLayoutManager(requireContext(), 2)
            unsuccessfulCallButton.setOnClickListener {
                binding.progressBar.isVisible = true
                snacksViewModel.getSnacksFromApi()
                binding.unsuccessfulCallButton.isVisible = false
                binding.unsuccessfulCallTv.isVisible = false
            }
        }

        snacksViewModel.snacksLiveData.observe(viewLifecycleOwner) { snacksList ->
            if (snacksList == null || snacksList.isEmpty()) {
                binding.apply {
                    unsuccessfulCallButton.isVisible = true
                    unsuccessfulCallTv.isVisible = true
                    progressBar.isVisible = false
                }
                return@observe
            }
            adapter.differ.submitList(snacksList)
            binding.progressBar.isVisible = false
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}