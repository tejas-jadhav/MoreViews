package com.example.moreviews.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.moreviews.R
import com.example.moreviews.databinding.FragmentConvertBinding


class ConvertFragment : Fragment(R.layout.fragment_convert) {
    private lateinit var binding: FragmentConvertBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentConvertBinding.bind(view)
    }

}