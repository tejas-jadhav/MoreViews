package com.example.moreviews.ui.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import com.example.moreviews.R
import com.example.moreviews.databinding.FragmentConvertBinding
import java.text.DecimalFormat


class ConvertFragment : Fragment(R.layout.fragment_convert) {
    private lateinit var binding: FragmentConvertBinding
    private val TAG = "ConvertFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentConvertBinding.bind(view)
        binding.tvInput.text = "0"
        binding.spinInput.setSelection(2)
        updateTarget()
        setButtonOnClickListeners()
        setSpinnerItemClickListeners()
    }


    private fun updateInput(number: String) {
        when {
            getInput() == "0" && number == "." -> binding.tvInput.text = "0."
            getInput() == "0" -> binding.tvInput.text = number
            getInput().contains(".") && number == "." -> return
            else -> {
                val newInput = getInput() + number
                binding.tvInput.text = newInput
            }
        }
        updateTarget()
    }

    private fun getInput(): String {
        return binding.tvInput.text.toString()
    }

    private fun removeOneCharacter() {
        if (getInput().length == 1) {
            binding.tvInput.text = "0"
            updateTarget()
            return
        }
        val newInput = getInput().substring(0, getInput().length - 1)
        binding.tvInput.text = newInput
        updateTarget()
    }

    private fun removeAllCharacters() {
        binding.tvInput.text = "0"
        updateTarget()
    }

    private fun setButtonOnClickListeners() {
        binding.btn0.setOnClickListener { updateInput("0") }
        binding.btn1.setOnClickListener { updateInput("1") }
        binding.btn2.setOnClickListener { updateInput("2") }
        binding.btn3.setOnClickListener { updateInput("3") }
        binding.btn4.setOnClickListener { updateInput("4") }
        binding.btn5.setOnClickListener { updateInput("5") }
        binding.btn6.setOnClickListener { updateInput("6") }
        binding.btn7.setOnClickListener { updateInput("7") }
        binding.btn8.setOnClickListener { updateInput("8") }
        binding.btn9.setOnClickListener { updateInput("9") }
        binding.btnDot.setOnClickListener { updateInput(".") }
        binding.btnC.setOnClickListener { removeOneCharacter() }
        binding.btnC.setOnLongClickListener {
            removeAllCharacters()
            true
        }
    }

    private fun setSpinnerItemClickListeners() {
        binding.spinTarget.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                adapterView: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                updateTarget()
            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {}
        }

        binding.spinInput.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                adapterView: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                updateTarget()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }
    }

    private fun updateTarget() {
        Log.d(
            TAG,
            "input: ${binding.spinInput.selectedItem} target: ${binding.spinTarget.selectedItem}"
        )
        val factor = getTargetMultiplicationFactor(
            binding.spinInput.selectedItem.toString(),
            binding.spinTarget.selectedItem.toString()
        )
        val result = getInput().toDouble() * factor
        binding.tvTarget.text = if (result == 0.0) "0" else DecimalFormat("0.###").format(result)

    }


    private fun getTargetMultiplicationFactor(input: String, target: String): Double {
        return when {
            input == target -> 1.0
            input == "Bit (b)" && target == "Byte (B)" -> 1.0 / 8
            input == "Bit (b)" && target == "KB" ->   1.0 / (8 * 1024)
            input == "Bit (b)" && target == "MB" ->   1.0 / (8 * 1024 * 1024)
            input == "Bit (b)" && target == "GB" ->   0.0
            input == "Bit (b)" && target == "TB" ->   0.0

            input == "Byte (B)" && target == "Bit (b)" -> 8.0
            input == "Byte (B)" && target == "KB" -> 1.0 / (1024)
            input == "Byte (B)" && target == "MB" -> 1.0 / (1024 * 1024)
            input == "Byte (B)" && target == "GB" -> 1.0 / (1024 * 1024 * 1024)
            input == "Byte (B)" && target == "TB" -> 0.0

            input == "KB" && target == "Bit (b)" -> 8.0 * 1024
            input == "KB" && target == "Byte (B)" -> 1024.0
            input == "KB" && target == "MB" -> 1.0 / 1024.0
            input == "KB" && target == "GB" -> 1.0 / (1024.0 * 1024.0)
            input == "KB" && target == "TB" -> 1.0 / (1024.0 * 1024.0 * 1024.0)

            input == "MB" && target == "Bit (b)" -> 8.0 * 1024 * 1024
            input == "MB" && target == "Byte (B)" -> 1024.0 * 1024
            input == "MB" && target == "KB" ->  1024.0
            input == "MB" && target == "GB" -> 1.0 / (1024.0)
            input == "MB" && target == "TB" -> 1.0 / (1024.0 * 1024.0)

            input == "GB" && target == "Bit (b)" -> 8.0 * 1024 * 1024 * 1024
            input == "GB" && target == "Byte (B)" -> 1024.0 * 1024 * 1024
            input == "GB" && target == "MB" -> 1024.0 * 1024
            input == "GB" && target == "MB" -> (1024.0)
            input == "GB" && target == "TB" -> 1.0 / (1024.0 )

            input == "TB" && target == "Bit (b)" -> 8.0 * 1024 * 1024 * 1024 * 1024.0
            input == "TB" && target == "Byte (B)" -> 1024.0 * 1024 * 1024 * 1024.0
            input == "TB" && target == "KB" -> 1024.0 * 1024 *1024.0
            input == "TB" && target == "MB" -> (1024.0 * 1024.0)
            input == "TB" && target == "GB" -> 1024.0

            else -> 1.0
        }
    }

}