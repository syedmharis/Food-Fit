package com.example.foodfit.ui.screens.onboardings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.foodfit.R
import com.example.foodfit.databinding.FragmentActivityLevelBinding

class ActivityLevelScreen : Fragment() {

    private var _binding: FragmentActivityLevelBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentActivityLevelBinding.inflate(inflater, container, false)
        val view = binding.root

        val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPager)

        binding.activitySelectionButtonNext.setOnClickListener {
            viewPager?.currentItem = 9
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
