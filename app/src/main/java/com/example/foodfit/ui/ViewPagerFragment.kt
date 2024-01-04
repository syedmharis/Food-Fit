package com.example.foodfit.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.foodfit.databinding.FragmentViewPagerBinding
import com.example.foodfit.ui.screens.onboardings.FirstScreen
import com.example.foodfit.ui.screens.onboardings.GetStartedScreen
import com.example.foodfit.ui.screens.onboardings.SecondScreen
import com.example.foodfit.ui.screens.onboardings.ThirdScreen
import com.example.foodfit.ui.screens.onboardings.activity_level.ActivityLevelScreen
import com.example.foodfit.ui.screens.onboardings.age.AgeScreen
import com.example.foodfit.ui.screens.onboardings.gender.GenderSelectScreen
import com.example.foodfit.ui.screens.onboardings.height.HeightScreen
import com.example.foodfit.ui.screens.onboardings.nutrient_goal.NutrientGoalScreen
import com.example.foodfit.ui.screens.onboardings.save_weight.WeightInputScreen
import com.example.foodfit.ui.screens.onboardings.weight_choice_screen.WeightChoiceScreen

class ViewPagerFragment : Fragment() {

    private var _binding: FragmentViewPagerBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentViewPagerBinding.inflate(inflater, container, false)
        val view = binding.root

        val fragmentList = arrayListOf<Fragment>(
            FirstScreen(),
            SecondScreen(),
            ThirdScreen(),
            GetStartedScreen(),
            GenderSelectScreen(),
            AgeScreen(),
            WeightInputScreen(),
            HeightScreen(),
            ActivityLevelScreen(),
            NutrientGoalScreen(),
            WeightChoiceScreen()
        )

            val adapter = ViewPagerAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle
        )

        binding.viewPager.adapter = adapter

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
