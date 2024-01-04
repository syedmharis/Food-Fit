package com.example.foodfit.ui.screens.home_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.foodfit.R
import com.example.foodfit.data.datastore.CalorieTrackerDataStore
import com.example.foodfit.databinding.FragmentHomeBinding
import com.example.foodfit.domain.food_tracker.model.TrackedFood
import com.example.foodfit.domain.food_tracker.use_cases.CalculateMealNutrients
import com.example.foodfit.utils.UnitSpanText
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private lateinit var displayNameTextView: TextView
    private var _binding: FragmentHomeBinding? = null
    private var spanClass = UnitSpanText
    private val binding get() = _binding!!
    private lateinit var calorieTrackerDataStore: CalorieTrackerDataStore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root

        calorieTrackerDataStore = CalorieTrackerDataStore(requireContext())
        var calculateMealNutrients = CalculateMealNutrients(calorieTrackerDataStore)

        // Find the TextView in your layout
        displayNameTextView = view.findViewById(R.id.your_goal)

        // Retrieve the current user from Firebase Authentication
        val currentUser = FirebaseAuth.getInstance().currentUser
        var fullName = currentUser?.displayName
        var lastName = fullName?.split(" ")?.getOrNull(2)

        // Check if the user is signed in
        if (currentUser != null) {
            // User is signed in, set the display name to the TextView
            displayNameTextView.text = "Hello, $lastName your goal is"
        } else {
            // User is not signed in, handle this case as needed
            displayNameTextView.text = "Hello, Guest"
        }

        binding.addBreakfastButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_search_screen)
        }

        var currentCalories = spanClass.spanUnitText(binding.current.text)
        binding.current.text = currentCalories

        // Collect the user info and update the goal calories
        viewLifecycleOwner.lifecycleScope.launch {
            calorieTrackerDataStore.readUserInfo.collect {
                val trackedFoods = listOf<TrackedFood>()
                val result = calculateMealNutrients(trackedFoods)

                val goalCalories = spanClass.spanUnitText("${result.caloriesGoal} Kcal")
                binding.goal.text = goalCalories

                val currentCalories = spanClass.spanUnitText("${result.totalCalories} Kcal")
                binding.current.text = currentCalories

                var myCarbs = result.totalCarbs
                binding.carbsLabel.text = "Carbs: ${myCarbs}g"

                var myProtein = result.totalProtein
                binding.proteinLabel.text = "Protein: ${myProtein}g"

                var myFat = result.totalFat
                binding.fatLabel.text = "Fat: ${myFat}g"
            }
        }



        return view
    }

}
