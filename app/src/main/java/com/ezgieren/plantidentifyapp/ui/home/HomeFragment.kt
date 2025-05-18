package com.ezgieren.plantidentifyapp.ui.home

import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Shader
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ezgieren.plantidentifyapp.databinding.FragmentHomeBinding
import com.ezgieren.plantidentifyapp.ui.paywall.CategoryAdapter
import com.ezgieren.plantidentifyapp.utils.Resource
import com.ezgieren.plantidentifyapp.viewmodel.CategoryViewModel
import com.ezgieren.plantidentifyapp.viewmodel.QuestionViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val questionViewModel: QuestionViewModel by viewModels()
    private val categoryViewModel: CategoryViewModel by viewModels()

    private lateinit var questionAdapter: QuestionAdapter
    private lateinit var categoryAdapter: CategoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupRecyclerViews()
        observeQuestions()
        observeCategories()
        questionViewModel.fetchQuestions()
        categoryViewModel.fetchCategories()
        applyPremiumTitleGradient()
    }

    private fun setupRecyclerViews() {
        // Horizontal question list
        questionAdapter = QuestionAdapter()

        binding.rvQuestions.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = questionAdapter
        }

        // Grid category list
        categoryAdapter = CategoryAdapter { category ->
            // setClick
        }
        binding.rvCategories.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = categoryAdapter
        }
    }

    private fun observeQuestions() {
        lifecycleScope.launch {
            questionViewModel.questionState.collectLatest { state ->
                when (state) {
                    is Resource.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                        binding.tvError.visibility = View.GONE
                    }
                    is Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        binding.tvError.visibility = View.GONE
                        questionAdapter.submitList(state.data)
                    }
                    is Resource.Error -> {
                        binding.progressBar.visibility = View.GONE
                        binding.tvError.visibility = View.VISIBLE
                        binding.tvError.text = state.message
                        Toast.makeText(requireContext(), state.detailedMessage, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun observeCategories() {
        lifecycleScope.launch {
            categoryViewModel.categoryState.collectLatest { state ->
                when (state) {
                    is Resource.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                        binding.tvError.visibility = View.GONE
                    }

                    is Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        binding.tvError.visibility = View.GONE
                        categoryAdapter.submitList(state.data)
                    }

                    is Resource.Error -> {
                        binding.progressBar.visibility = View.GONE
                        binding.tvError.visibility = View.VISIBLE
                        binding.tvError.text = state.message
                        Toast.makeText(requireContext(), state.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun applyPremiumTitleGradient() {
        binding.tvPremiumTitle.post {
            val textView = binding.tvPremiumTitle
            val text = textView.text.toString()
            val paint = textView.paint
            val width = paint.measureText(text)

            val gradient = LinearGradient(
                0f, 0f, width, 0f,
                intArrayOf(
                    Color.parseColor("#F0D399"),
                    Color.parseColor("#D9A846")
                ),
                null,
                Shader.TileMode.CLAMP
            )
            paint.shader = gradient
            textView.invalidate()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}