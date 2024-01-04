package com.example.foodfit.ui.screens.onboardings.gender

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.foodfit.R
import com.example.foodfit.data.datastore.CalorieTrackerDataStore
import com.example.foodfit.databinding.FragmentGenderSelectionBinding
import com.example.foodfit.domain.on_boarding.model.Gender

class GenderSelectScreen : Fragment() {

    private var _binding: FragmentGenderSelectionBinding? = null
    private val binding get() = _binding!!

    private lateinit var calorieTrackerDataStore: CalorieTrackerDataStore
    private lateinit var genderViewModel: GenderViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGenderSelectionBinding.inflate(inflater, container, false)
        val view = binding.root

        val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPager)

        // Initialize CalorieTrackerDataStore and GenderViewModel here
        calorieTrackerDataStore = CalorieTrackerDataStore(requireContext())
        genderViewModel = GenderViewModel(calorieTrackerDataStore)

        // Set default gender selection to Male
        binding.toggleButton.check(R.id.male)

        binding.male.setOnClickListener { onGenderButtonClick(Gender.Male) }
        binding.female.setOnClickListener { onGenderButtonClick(Gender.Female) }

        binding.genderSelectionButtonNext.setOnClickListener {
            genderViewModel.saveToPreference()
            viewPager?.currentItem = 5
        }

        return view
    }

    private fun onGenderButtonClick(selectedGender: Gender) {
        binding.toggleButton.check(if (selectedGender == Gender.Male) R.id.male else R.id.female)
        binding.toggleButton.uncheck(if (selectedGender == Gender.Male) R.id.female else R.id.male)
        genderViewModel.onGenderClick(selectedGender)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
