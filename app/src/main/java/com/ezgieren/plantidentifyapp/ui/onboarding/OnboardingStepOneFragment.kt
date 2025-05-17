package com.ezgieren.plantidentifyapp.ui.onboarding

import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.ezgieren.plantidentifyapp.R
import com.ezgieren.plantidentifyapp.databinding.FragmentOnboardingStepOneBinding

class OnboardingStepOneFragment : Fragment() {

    private var _binding: FragmentOnboardingStepOneBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOnboardingStepOneBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fullText = getString(R.string.onboarding_title_1) // "Take a photo to identify the plant!"
        val spannable = SpannableString(fullText)

        // "identify" bold
        val startIndex = fullText.indexOf("identify")
        val endIndex = startIndex + "identify".length

        if (startIndex >= 0) {
            spannable.setSpan(
                StyleSpan(Typeface.BOLD),
                16, 24,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }

        binding.tvTitle.text = spannable
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}