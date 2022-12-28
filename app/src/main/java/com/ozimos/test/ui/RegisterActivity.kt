package com.ozimos.test.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.ozimos.test.databinding.ActivityRegisterBinding
import com.ozimos.test.util.StateUtil

class RegisterActivity : AppCompatActivity() {

    private val binding by lazy { ActivityRegisterBinding.inflate(layoutInflater) }
    private val authViewModel: AppVIewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setView()
        setObserver()
    }

    private fun setObserver() {
        registerObserver()
    }

    private fun registerObserver() {
        authViewModel.registerData.observe(this) { state ->
            when (state) {
                is StateUtil.Loading -> {
                    showToast("Registration Process")
                }
                is StateUtil.Failed -> {
                    showToast(state.message)
                }
                is StateUtil.Success -> {
                    showToast(state.message)
                    onBackPressedDispatcher.onBackPressed()
                }
            }
        }
    }

    private fun setView() {
        binding.run {
            btnRegister.setOnClickListener {
                if (edtUsername.text.isNullOrEmpty()) {
                    showToast("Username must be fill")
                    return@setOnClickListener
                }
                if (edtPassword.text.isNullOrEmpty()) {
                    showToast("Password must be fill")
                    return@setOnClickListener
                }

                if (edtEmail.text.isNullOrEmpty()) {
                    showToast("Email must be fill")
                    return@setOnClickListener
                }

                authViewModel.register(
                    username = edtUsername.text.toString().trim(),
                    password = edtPassword.text.toString().trim(),
                    email = edtEmail.text.toString().trim()
                )
            }
        }
    }

    private fun showToast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }
}