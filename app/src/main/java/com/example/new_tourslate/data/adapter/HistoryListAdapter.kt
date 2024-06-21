package com.example.new_tourslate.data.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.new_tourslate.data.response.Data
import com.example.new_tourslate.data.response.DataItem
import com.example.new_tourslate.data.response.History
import com.example.new_tourslate.databinding.ItemHistoryBinding

class HistoryListAdapter :
    PagingDataAdapter<DataItem, HistoryListAdapter.MyViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val history = getItem(position)
        Log.d("HistoryListAdapter", "Binding data at position $position: $history")
        if (history != null) {
            holder.bind(history)
        }
    }

    class MyViewHolder(private val binding: ItemHistoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(history: DataItem) {
            binding.tvOriginal.text = history.history!!.originalText
            binding.tvTranslate.text = history.history.translatedText
            Log.d("MyViewHolder", "Bound history: $history")
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DataItem>() {
            override fun areItemsTheSame(
                oldItem: DataItem,
                newItem: DataItem
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: DataItem,
                newItem: DataItem
            ): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
    }