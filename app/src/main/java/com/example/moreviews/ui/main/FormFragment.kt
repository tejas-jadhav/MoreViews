package com.example.moreviews.ui.main

import android.content.DialogInterface
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.moreviews.R
import com.example.moreviews.databinding.FragmentFormBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class FormFragment : Fragment(R.layout.fragment_form) {
    private lateinit var binding: FragmentFormBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFormBinding.bind(view)


        binding.btnForgotPassword.setOnClickListener {
            Toast.makeText(this.context, "Then Just Remember it", Toast.LENGTH_SHORT).show()
        }
        binding.btnSignIn.setOnClickListener {
            binding.emailField.isHelperTextEnabled = true
            binding.passwordField.isHelperTextEnabled = true
            val emailValidated = validateEmail()
            val passwordValidated = validatePassword()

            if (emailValidated && passwordValidated) {
                if (isAdded) {
                    MaterialAlertDialogBuilder(requireContext()).apply {
                        setTitle("Your details !")
                        setMessage("So your email is ... ${binding.emailField.editText?.text}")
                        setPositiveButton("Duh !") { dialogInterface: DialogInterface, i: Int ->
                            dialogInterface.dismiss()
                        }
                    }.show()
                }
            }
        }


    }

    private fun validatePassword(): Boolean {
        if (binding.passwordField.editText == null) {
            binding.passwordField.helperText = "Password where ?"
            return false
        }
        if (binding.passwordField.editText?.text.toString() == "") {
            binding.passwordField.helperText = "Password where ?"
            return false
        }
        if (binding.passwordField.editText?.text.toString().length < 8) {
            binding.passwordField.helperText = "Password should contain minimum 8 characters"
            return false
        }
        binding.passwordField.isHelperTextEnabled = false
        return true
    }

    private fun validateEmail(): Boolean {
        if (binding.emailField.editText == null) {
            binding.emailField.helperText = "Email where ?"
            return false
        }
        if (binding.emailField.editText?.text.toString() == "") {
            binding.emailField.helperText = "Email where ?"
            return false
        }

        binding.emailField.editText?.let {
            if (!Patterns.EMAIL_ADDRESS.matcher(it.text).matches()) {
                binding.emailField.helperText = "Enter a valid email"
                return false
            }
        }
        binding.emailField.isHelperTextEnabled = false
        return true
    }

}