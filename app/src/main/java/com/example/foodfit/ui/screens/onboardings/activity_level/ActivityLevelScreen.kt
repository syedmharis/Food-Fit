package com.example.foodfit.ui.screens.onboardings.activity_level

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.foodfit.R
import com.example.foodfit.data.datastore.CalorieTrackerDataStore
import com.example.foodfit.databinding.FragmentActivityLevelBinding
import com.example.foodfit.domain.on_boarding.model.ActivityLevel

class ActivityLevelScreen : Fragment() {

    private var _binding: FragmentActivityLevelBinding? = null
    private val binding get() = _binding!!

    private lateinit var calorieTrackerDataStore: CalorieTrackerDataStore
    private lateinit var activityLevelViewModel: ActivityLevelViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentActivityLevelBinding.inflate(inflater, container, false)
        val view = binding.root

        val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPager)

        // Initialize CalorieTrackerDataStore and GenderViewModel here
        calorieTrackerDataStore = CalorieTrackerDataStore(requireContext())
        activityLevelViewModel = ActivityLevelViewModel(calorieTrackerDataStore)

        // Set default gender selection to medium
        binding.toggleButton.check(R.id.medium)
        binding.low.setOnClickListener {
            binding.toggleButton.check(R.id.low)
            binding.toggleButton.uncheck(R.id.medium)
            binding.toggleButton.uncheck(R.id.high)
            activityLevelViewModel.onActivityLevelSelected(ActivityLevel.Low)
        }
        binding.medium.setOnClickListener {
            binding.toggleButton.uncheck(R.id.low)
            binding.toggleButton.check(R.id.medium)
            binding.toggleButton.uncheck(R.id.high)
            activityLevelViewModel.onActivityLevelSelected(ActivityLevel.Medium)
        }
        binding.high.setOnClickListener {
            binding.toggleButton.uncheck(R.id.low)
            binding.toggleButton.uncheck(R.id.medium)
            binding.toggleButton.check(R.id.high)
            activityLevelViewModel.onActivityLevelSelected(ActivityLevel.High)
        }

        binding.activitySelectionButtonNext.setOnClickListener {
            activityLevelViewModel.onNextClick()
            viewPager?.currentItem = 9
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
