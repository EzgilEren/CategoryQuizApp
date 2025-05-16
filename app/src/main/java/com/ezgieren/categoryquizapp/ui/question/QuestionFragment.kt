package com.ezgieren.categoryquizapp.ui.question

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.ezgieren.categoryquizapp.R
import com.ezgieren.categoryquizapp.databinding.QuestionFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuestionFragment : Fragment() {

    private var _binding: QuestionFragmentBinding? = null
    private val binding get() = _binding!!

    private val args: QuestionFragmentArgs by navArgs() // SafeArgs ile gelen categoryId

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = QuestionFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val categoryId = args.categoryId
        binding.tvCategoryId.text = getString(R.string.question_category_id, categoryId)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}