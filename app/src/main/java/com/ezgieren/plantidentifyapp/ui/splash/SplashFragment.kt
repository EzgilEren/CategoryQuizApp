package com.ezgieren.plantidentifyapp.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ezgieren.plantidentifyapp.R
import com.ezgieren.plantidentifyapp.utils.PreferencesManager
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : Fragment() {

    private val prefsManager = PreferencesManager()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View = FrameLayout(requireContext())

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.postDelayed({
            val direction = if (prefsManager.isOnboardingComplete(requireContext())) {
                R.id.action_splash_to_home
            } else {
                R.id.action_splash_to_get_started
            }
            findNavController().navigate(direction)
        }, 300)
    }
}