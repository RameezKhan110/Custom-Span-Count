package com.example.gridlayout.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.gridlayout.databinding.SampleItemBinding
import com.example.gridlayout.model.ItemModel

class SimpleItemAdapter : ListAdapter<ItemModel, RecyclerView.ViewHolder>(NormalDiffUtil()) {

    class NormalDiffUtil : DiffUtil.ItemCallback<ItemModel>() {
        override fun areItemsTheSame(oldItem: ItemModel, newItem: ItemModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: ItemModel, newItem: ItemModel): Boolean {
            return oldItem == newItem
        }
    }

    inner class NormalAdapterViewHolder(private val binding: SampleItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return NormalAdapterViewHolder(
            SampleItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        SampleItemBinding.bind(holder.itemView).apply {
            textView.text = item.item
        }
    }
}