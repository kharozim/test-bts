package com.ozimos.test.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.ozimos.test.databinding.ActivityMainBinding
import com.ozimos.test.util.AccountHelper
import com.ozimos.test.util.StateUtil
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private val authViewModel: AuthVIewModel by viewModels()
    private val accountHelper by lazy { AccountHelper(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setView()
        setObserver()

    }

    private fun setObserver() {
        loginObserver()
    }

    private fun loginObserver() {
        authViewModel.loginData.observe(this) { state ->
            when (state) {
                is StateUtil.Loading -> {
                    showToast("Login Process")
                }
                is StateUtil.Failed -> {
                    showToast(state.message)
                }
                is StateUtil.Success -> {
                    showToast(state.data.token ?: "")
                    accountHelper.setAuthToken(state.data.token ?: "")
                }
            }
        }
    }

    private fun setView() {
        binding.run {
            tvRegister.setOnClickListener {
               startActivity(Intent(this@MainActivity, RegisterActivity::class.java))
            }
            btnLogin.setOnClickListener {
                if (edtUsername.text.isNullOrEmpty()) {
                    showToast("Username must be fill")
                    return@setOnClickListener
                }
                if (edtPassword.text.isNullOrEmpty()) {
                    showToast("Password must be fill")
                    return@setOnClickListener
                }

                authViewModel.login(
                    username = edtUsername.text.toString().trim(),
                    password = edtPassword.text.toString().trim()
                )
            }
        }
    }

    private fun showToast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

}