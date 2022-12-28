package com.ozimos.test.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.window.OnBackInvokedDispatcher
import androidx.activity.viewModels
import com.ozimos.test.data.response.CheckListResponse
import com.ozimos.test.databinding.ActivityHomeBinding
import com.ozimos.test.util.StateUtil
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    private val binding by lazy { ActivityHomeBinding.inflate(layoutInflater) }
    private val homeViewModel: AppVIewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        getData()
        setView()
        setObserver()
    }

    private fun setObserver() {
        homeViewModel.checkList.observe(this) { state ->
            when (state) {
                is StateUtil.Loading -> {
                }
                is StateUtil.Failed -> {
                    showToast(state.message)
                }
                is StateUtil.Success -> {
                    setData(state.data)
                }
            }
        }
    }

    private fun setData(data: List<CheckListResponse>) {
        val adapter = ChecklistAdapter(data)
        binding.run {
            rvHome.adapter = adapter
        }
    }

    private fun getData() {
        homeViewModel.getChecklit()
    }

    private fun setView() {

    }

    private fun showToast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }
}