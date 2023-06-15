package com.trashcare.user.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.trashcare.user.data.model.historydata.HistoryData
import com.trashcare.user.data.model.response.history.HistoryResponseItem
import com.trashcare.user.databinding.ItemHistoryBinding

class HistoryListAdapter(): RecyclerView.Adapter<HistoryListAdapter.ListViewHolder>() {
    private val listHistory = mutableListOf<HistoryResponseItem>()
    inner class ListViewHolder(private var binding: ItemHistoryBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: HistoryResponseItem){
            binding.apply {
                trashId.text = item.trashId
                descHistory.text = item.description
                tvLocationHistory.text = item.location
                status.text = item.status
                total.text = item.total.toString()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun getItemCount(): Int = listHistory.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        listHistory[position].let { holder.bind(it) }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<HistoryResponseItem>) {
        listHistory.clear()
        listHistory.addAll(data)
        notifyDataSetChanged()
    }
}