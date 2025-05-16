package com.ezgieren.plantidentifyapp.ui.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.ezgieren.plantidentifyapp.databinding.CategoryFragmentBinding
import com.ezgieren.plantidentifyapp.utils.Constants
import com.ezgieren.plantidentifyapp.utils.Resource
import com.ezgieren.plantidentifyapp.viewmodel.CategoryViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CategoryFragment : Fragment() {

    private var _binding: CategoryFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CategoryViewModel by viewModels()

    private lateinit var categoryAdapter: CategoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = CategoryFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupRecyclerView()
        setupObservers()
        setupRetryButton()
        viewModel.fetchCategories()
    }

    private fun setupRecyclerView() {
        categoryAdapter = CategoryAdapter { category ->
            val action = CategoryFragmentDirections
                .actionCategoryFragmentToQuestionFragment(category.id)
            findNavController().navigate(action)
        }

        binding.rvCategories.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = categoryAdapter
            setHasFixedSize(true)
        }
    }

    private fun setupObservers() {
        lifecycleScope.launch {
            viewModel.categoryState.collectLatest { state ->
                when (state) {
                    is Resource.Loading -> {
                        showLoading(true)
                        showError(false)
                    }
                    is Resource.Success -> {
                        showLoading(false)
                        showError(false)
                        categoryAdapter.submitList(state.data)
                    }
                    is Resource.Error -> {
                        showLoading(false)
                        showError(true, state.message ?: Constants.DEFAULT_ERROR_MESSAGE)
                    }
                }
            }
        }
    }

    private fun setupRetryButton() {
        binding.btnRetry.setOnClickListener {
            viewModel.fetchCategories()
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun showError(isError: Boolean, message: String = "") {
        binding.tvError.visibility = if (isError) View.VISIBLE else View.GONE
        binding.btnRetry.visibility = if (isError) View.VISIBLE else View.GONE
        binding.tvError.text = message
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}