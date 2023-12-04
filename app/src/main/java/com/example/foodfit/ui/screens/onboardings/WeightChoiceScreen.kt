package com.example.foodfit.ui.screens.onboardings

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.foodfit.R
import com.example.foodfit.databinding.FragmentWeightChoiceBinding

class WeightChoiceScreen : Fragment() {

    private var _binding: FragmentWeightChoiceBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWeightChoiceBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.weightChoiceSelectionButtonNext.setOnClickListener {
            findNavController().navigate(R.id.action_viewPagerFragment_to_homeFragment)
            onBoardingFinished()
        }
        return view
    }

        private fun onBoardingFinished() {
        val sharedPref =
            requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putBoolean("Finished", true)
        editor.apply()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
