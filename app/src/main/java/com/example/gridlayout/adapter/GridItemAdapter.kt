package com.example.gridlayout.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.gridlayout.databinding.ItemRowLayoutBinding
import com.example.gridlayout.databinding.SampleItemBinding
import com.example.gridlayout.model.ItemModel

class GridItemAdapter : ListAdapter<ItemModel, RecyclerView.ViewHolder>(ParentDiffUtil()) {

    class ParentDiffUtil : DiffUtil.ItemCallback<ItemModel>() {
        override fun areItemsTheSame(oldItem: ItemModel, newItem: ItemModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: ItemModel, newItem: ItemModel): Boolean {
            return oldItem == newItem
        }
    }

    inner class AdapterViewHolder(private val binding: ItemRowLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ItemModel) {

            item.listItems?.forEach {
                val sampleItemBinding =
                    SampleItemBinding.inflate(LayoutInflater.from(binding.root.context))

                sampleItemBinding.textView.text = it
                binding.llRow.addView(sampleItemBinding.root)

            }
            Log.d("TAG", "calling from adapter: " + item.listItems)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return AdapterViewHolder(
            ItemRowLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        (holder as AdapterViewHolder).bind(ItemModel(listItems = item.listItems ?: arrayListOf()))

    }
}