package com.ezgieren.plantidentifyapp.ui.start

import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.StyleSpan
import android.text.style.UnderlineSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ezgieren.plantidentifyapp.R
import com.ezgieren.plantidentifyapp.databinding.GetStartedFragmentBinding
import com.ezgieren.plantidentifyapp.utils.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GetStartedFragment : Fragment() {

    private var _binding: GetStartedFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = GetStartedFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // Terms & Privacy spannable
        val builder = SpannableStringBuilder().apply {
            append(Constants.TERMS_PREFIX)

            val startTerms = length
            append(Constants.TERMS_OF_USE)
            setSpan(StyleSpan(Typeface.BOLD), startTerms, length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            setSpan(UnderlineSpan(), startTerms, length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

            append(" & ")

            val startPrivacy = length
            append(Constants.PRIVACY_POLICY)
            setSpan(StyleSpan(Typeface.BOLD), startPrivacy, length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            setSpan(UnderlineSpan(), startPrivacy, length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        }

        binding.tvTerms.text = builder

        // Button click → navigate
        binding.btnStart.setOnClickListener {
            findNavController().navigate(R.id.action_getStarted_to_onboarding)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}