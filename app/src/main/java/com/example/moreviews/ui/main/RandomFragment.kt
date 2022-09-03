package com.example.moreviews.ui.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.moreviews.R
import com.example.moreviews.databinding.FragmentRandomBinding
import com.google.android.material.slider.RangeSlider
import kotlin.math.max


class RandomFragment : Fragment(R.layout.fragment_random) {
    private lateinit var binding: FragmentRandomBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRandomBinding.bind(view)
        var minRange = binding.sliderRange.values[0].toDouble()
        var maxRange = binding.sliderRange.values[1].toDouble()

        binding.tvRandomNumber.text = "69"

        binding.sliderRange.addOnChangeListener { rangeSlider: RangeSlider, _, _ ->
            minRange = rangeSlider.values[0].toDouble()
            maxRange = rangeSlider.values[1].toDouble()
        }

        binding.btnGenerateRandom.setOnClickListener {
            val randomNumber = generateRandomNumberInRange(minRange, maxRange)
            binding.tvRandomNumber.text = randomNumber.toString()
        }
    }

    private fun generateRandomNumberInRange(start: Double, end:Double) : Int {
        return (Math.random() * (end - start + 1)).toInt() + start.toInt()
    }

}