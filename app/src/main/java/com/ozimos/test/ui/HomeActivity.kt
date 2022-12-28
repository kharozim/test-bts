package com.ozimos.test.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ozimos.test.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private val binding by lazy { ActivityHomeBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        getData()
        setView()
    }

    private fun getData() {

    }

    private fun setView() {

    }
}