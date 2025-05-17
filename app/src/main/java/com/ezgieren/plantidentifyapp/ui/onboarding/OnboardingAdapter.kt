package com.ezgieren.plantidentifyapp.ui.onboarding

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ezgieren.plantidentifyapp.utils.Constants

class OnboardingAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = Constants.ONBOARDING_PAGE_COUNT

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            Constants.ONBOARDING_PAGE_ONE -> OnboardingStepOneFragment()
            Constants.ONBOARDING_PAGE_TWO -> OnboardingStepTwoFragment()
            else -> throw IllegalStateException(Constants.INVALID_ONBOARDING_POSITION)
        }
    }
}