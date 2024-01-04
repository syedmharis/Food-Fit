package com.example.foodfit.ui.screens.onboardings.save_weight

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.foodfit.R
import com.example.foodfit.data.datastore.CalorieTrackerDataStore
import com.example.foodfit.databinding.FragmentWeightInputBinding
import com.example.foodfit.utils.CheckInput


class WeightInputScreen : Fragment() {
    private lateinit var calorieTrackerDataStore: CalorieTrackerDataStore
    private lateinit var saveWeightViewModel: SaveWeightViewModel
    private var _binding: FragmentWeightInputBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWeightInputBinding.inflate(inflater, container, false)
        val view = binding.root
        // Initialize CalorieTrackerDataStore and ViewModel here
        calorieTrackerDataStore = CalorieTrackerDataStore(requireContext())
        saveWeightViewModel = SaveWeightViewModel(calorieTrackerDataStore)
        val checker = CheckInput

        val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPager)

        val weightValue = binding.editTextWeight.text.toString()
        saveWeightViewModel.onWeightChange(weightValue)

        binding.weightInputButtonNext.setOnClickListener {
            val weightValue = binding.editTextWeight.text.toString()
            if (checker.isValid(weightValue)) {
                val weightValue = binding.editTextWeight.text.toString()
                saveWeightViewModel.onWeightChange(weightValue)
                saveWeightViewModel.saveToPreference()
                viewPager?.currentItem = 7

                // Hide the error message if it was previously shown
                binding.weightErrorMessage.visibility = View.GONE
            } else {
                // Show the error message
                binding.weightErrorMessage.text = "Invalid Weight"
                binding.weightErrorMessage.visibility = View.VISIBLE
            }
        }
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
