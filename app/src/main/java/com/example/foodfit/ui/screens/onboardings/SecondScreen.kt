package com.example.foodfit.ui.screens.onboardings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.foodfit.R
import com.example.foodfit.databinding.FragmentSecondScreenBinding

class SecondScreen : Fragment() {

    private var _binding: FragmentSecondScreenBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSecondScreenBinding.inflate(inflater, container, false)
        val view = binding.root

        val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPager)

        binding.next2.setOnClickListener {
            viewPager?.currentItem = 2
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
