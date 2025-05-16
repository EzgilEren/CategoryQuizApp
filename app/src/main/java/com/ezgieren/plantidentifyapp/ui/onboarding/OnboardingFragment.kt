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
        val items = listOf(
            OnboardingItem(
                R.drawable.onboarding1,
                getString(R.string.onboarding_title_1),
                getString(R.string.onboarding_desc_1)
            ),
            OnboardingItem(
                R.drawable.onboarding2,
                getString(R.string.onboarding_title_2),
                getString(R.string.onboarding_desc_2)
            )
        )

        onboardingAdapter = OnboardingAdapter(items)
        binding.viewPagerOnboarding.adapter = onboardingAdapter
        binding.dotsIndicator.setViewPager2(binding.viewPagerOnboarding)

        // Butonu sadece son sayfada göster
        binding.viewPagerOnboarding.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.btnGetStarted.visibility =
                    if (position == items.lastIndex) View.VISIBLE else View.INVISIBLE
            }
        })
    }

    private fun setupGetStartedButton() {
        binding.btnGetStarted.setOnClickListener {
            findNavController().navigate(R.id.action_onboarding_to_category)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}