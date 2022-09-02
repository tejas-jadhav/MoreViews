package com.example.moreviews.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.moreviews.R
import com.example.moreviews.databinding.FragmentRandomBinding


class RandomFragment : Fragment(R.layout.fragment_random) {
    private lateinit var binding: FragmentRandomBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRandomBinding.bind(view)
    }

}