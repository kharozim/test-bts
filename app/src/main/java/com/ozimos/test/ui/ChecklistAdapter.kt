package com.ozimos.test.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ozimos.test.data.response.CheckListResponse
import com.ozimos.test.databinding.ItemHomeBinding

class ChecklistAdapter(val items: List<CheckListResponse>) :
    RecyclerView.Adapter<ChecklistAdapter.MyViewHolder>() {
    inner class MyViewHolder(private val binding: ItemHomeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(item: CheckListResponse) {
            binding.tvName.text = item.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemHomeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindData(items[position])
    }

    override fun getItemCount(): Int = items.size
}