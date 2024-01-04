package com.example.foodfit.ui.screens.onboardings.nutrient_goal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.foodfit.R
import com.example.foodfit.data.datastore.CalorieTrackerDataStore
import com.example.foodfit.databinding.FragmentNutrientGoalBinding

class NutrientGoalScreen : Fragment() {

    private var _binding: FragmentNutrientGoalBinding? = null
    private val binding get() = _binding!!
    private lateinit var dataStore: CalorieTrackerDataStore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNutrientGoalBinding.inflate(inflater, container, false)
        val view = binding.root

        val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPager)
        dataStore = CalorieTrackerDataStore(requireContext())
        val nutrientGoalViewModel = NutrientGoalViewModel(dataStore)


        binding.nutrientInputButtonNext.setOnClickListener {
            viewPager?.currentItem = 10
            nutrientGoalViewModel.saveToPreference()
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
