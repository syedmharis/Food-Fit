package com.example.foodfit.ui.screens.onboardings.age

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.foodfit.R
import com.example.foodfit.data.datastore.CalorieTrackerDataStore
import com.example.foodfit.databinding.FragmentAgeInputBinding
import com.example.foodfit.utils.CheckInput


class AgeScreen : Fragment() {

    private var _binding: FragmentAgeInputBinding? = null
    private val binding get() = _binding!!

    private lateinit var calorieTrackerDataStore: CalorieTrackerDataStore
    private lateinit var ageViewModel: AgeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAgeInputBinding.inflate(inflater, container, false)
        val view = binding.root

        val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPager)

        // Initialize CalorieTrackerDataStore and ViewModel here
        calorieTrackerDataStore = CalorieTrackerDataStore(requireContext())
        ageViewModel = AgeViewModel(calorieTrackerDataStore)
        val checker = CheckInput

        val ageValue = binding.editTextAge.text.toString()
        ageViewModel.onAgeChange(ageValue)

        binding.ageInputButtonNext.setOnClickListener {
            val ageValue = binding.editTextAge.text.toString()
            if (checker.isValid(ageValue)) {
                ageViewModel.onAgeChange(ageValue)
                viewPager?.currentItem = 6
                ageViewModel.saveToPreference()

                // Hide the error message if it was previously shown
                binding.ageErrorMessage.visibility = View.GONE
            } else {
                // Show the error message
                binding.ageErrorMessage.text = "Invalid age"
                binding.ageErrorMessage.visibility = View.VISIBLE
            }
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
