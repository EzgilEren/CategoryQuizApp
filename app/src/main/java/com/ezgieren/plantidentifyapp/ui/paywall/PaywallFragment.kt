package com.ezgieren.plantidentifyapp.ui.paywall

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ezgieren.plantidentifyapp.R
import com.ezgieren.plantidentifyapp.databinding.PaywallFragmentBinding

class PaywallFragment : Fragment() {

    private var _binding: PaywallFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = PaywallFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupCloseButton()
        setupCTAButton()
    }

    private fun setupCloseButton() {
        binding.btnClose.setOnClickListener {
            // Back navigation
            findNavController().navigateUp()
        }
    }

    private fun setupCTAButton() {
        binding.btnTryFree.setOnClickListener {
            findNavController().navigate(R.id.action_paywall_to_homepage) //dummy root
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}