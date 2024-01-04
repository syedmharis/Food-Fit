package com.example.foodfit.ui.screens.onboardings.weight_choice_screen

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.foodfit.R
import com.example.foodfit.data.datastore.CalorieTrackerDataStore
import com.example.foodfit.databinding.FragmentWeightChoiceBinding
import com.example.foodfit.domain.on_boarding.model.GoalType

class WeightChoiceScreen : Fragment() {

    private var _binding: FragmentWeightChoiceBinding? = null
    private val binding get() = _binding!!
    private lateinit var calorieTrackerDataStore: CalorieTrackerDataStore
    private lateinit var weightChoiceViewModel: WeightChoiceViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWeightChoiceBinding.inflate(inflater, container, false)
        val view = binding.root

        // Initialize CalorieTrackerDataStore and GenderViewModel here
        calorieTrackerDataStore = CalorieTrackerDataStore(requireContext())
        weightChoiceViewModel = WeightChoiceViewModel(calorieTrackerDataStore)

        // Set default gender selection to medium
        binding.toggleButton.check(R.id.keep)
        binding.lose.setOnClickListener {
            binding.toggleButton.check(R.id.lose)
            binding.toggleButton.uncheck(R.id.keep)
            binding.toggleButton.uncheck(R.id.gain)
            weightChoiceViewModel.onGoalLevelSelected(GoalType.LoseWeight)
        }
        binding.keep.setOnClickListener {
            binding.toggleButton.uncheck(R.id.lose)
            binding.toggleButton.check(R.id.keep)
            binding.toggleButton.uncheck(R.id.gain)
            weightChoiceViewModel.onGoalLevelSelected(GoalType.KeepWeight)
        }
        binding.gain.setOnClickListener {
            binding.toggleButton.uncheck(R.id.lose)
            binding.toggleButton.uncheck(R.id.keep)
            binding.toggleButton.check(R.id.gain)
            weightChoiceViewModel.onGoalLevelSelected(GoalType.GainWeight)
        }

        binding.weightChoiceSelectionButtonNext.setOnClickListener {
            findNavController().navigate(R.id.action_viewPagerFragment_to_homeFragment)
            onBoardingFinished()
            weightChoiceViewModel.onNextClick()
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
