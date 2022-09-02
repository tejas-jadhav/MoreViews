package com.example.moreviews.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.moreviews.R
import com.example.moreviews.databinding.FragmentFormBinding

class FormFragment : Fragment(R.layout.fragment_form) {
    private lateinit var binding: FragmentFormBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFormBinding.bind(view)
    }

}