package com.ezgieren.plantidentifyapp.ui.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.ezgieren.plantidentifyapp.R
import com.ezgieren.plantidentifyapp.databinding.OnboardingFragmentBinding

class OnboardingFragment : Fragment() {

    private var _binding: OnboardingFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var onboardingAdapter: OnboardingAdapter

    private lateinit var viewPager: ViewPager2

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = OnboardingFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupViewPager()
        setupGetStartedButton()
    }

    private fun setupViewPager() {
        onboardingAdapter = OnboardingAdapter(this)
        viewPager = binding.viewPagerOnboarding
        viewPager.adapter = onboardingAdapter
        binding.dotsIndicator.setViewPager2(viewPager)
    }

    private fun setupGetStartedButton() {
        binding.btnGetStarted.setOnClickListener {
            when (viewPager.currentItem) {
                0 -> viewPager.currentItem = 1
                1 -> findNavController().navigate(R.id.action_onboarding_to_category)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}