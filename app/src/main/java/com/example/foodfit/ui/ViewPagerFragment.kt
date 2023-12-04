package com.example.foodfit.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.foodfit.databinding.FragmentViewPagerBinding
import com.example.foodfit.ui.screens.onboardings.ActivityLevelScreen
import com.example.foodfit.ui.screens.onboardings.AgeScreen
import com.example.foodfit.ui.screens.onboardings.FirstScreen
import com.example.foodfit.ui.screens.onboardings.GenderSelectScreen
import com.example.foodfit.ui.screens.onboardings.GetStartedScreen
import com.example.foodfit.ui.screens.onboardings.HeightScreen
import com.example.foodfit.ui.screens.onboardings.NutrientGoalScreen
import com.example.foodfit.ui.screens.onboardings.SecondScreen
import com.example.foodfit.ui.screens.onboardings.ThirdScreen
import com.example.foodfit.ui.screens.onboardings.WeightChoiceScreen
import com.example.foodfit.ui.screens.onboardings.WeightInputScreen

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
