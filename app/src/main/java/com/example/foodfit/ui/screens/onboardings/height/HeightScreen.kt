package com.example.foodfit.ui.screens.onboardings.height

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.foodfit.R
import com.example.foodfit.data.datastore.CalorieTrackerDataStore
import com.example.foodfit.databinding.FragmentHeightInputBinding
import com.example.foodfit.utils.CheckInput


class HeightScreen : Fragment() {

    private lateinit var calorieTrackerDataStore: CalorieTrackerDataStore
    private lateinit var heightViewModel: HeightViewModel
    private var _binding: FragmentHeightInputBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHeightInputBinding.inflate(inflater, container, false)
        val view = binding.root

        // Initialize CalorieTrackerDataStore and ViewModel here
        calorieTrackerDataStore = CalorieTrackerDataStore(requireContext())
        heightViewModel = HeightViewModel(calorieTrackerDataStore)
        val checker = CheckInput

        val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPager)

        val weightValue = binding.editTextHeight.text.toString()
        heightViewModel.onHeightChange(weightValue)

        binding.heightInputButtonNext.setOnClickListener {
            val heightValue = binding.editTextHeight.text.toString()
            if (checker.isValid(heightValue)) {
                val heightValue = binding.editTextHeight.text.toString()
                heightViewModel.onHeightChange(heightValue)
                heightViewModel.saveToPreference()
                viewPager?.currentItem = 8

                // Hide the error message if it was previously shown
                binding.heightErrorMessage.visibility = View.GONE
            } else {
                // Show the error message
                binding.heightErrorMessage.text = "Invalid Height"
                binding.heightErrorMessage.visibility = View.VISIBLE
            }
        }

        return view
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
