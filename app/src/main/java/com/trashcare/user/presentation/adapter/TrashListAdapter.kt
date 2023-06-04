package com.trashcare.user.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.trashcare.user.data.DataListTrash.TrashList
import com.trashcare.user.databinding.ItemTrashBinding

class TrashListAdapter(private val listTrash: List<TrashList>) : RecyclerView.Adapter<TrashListAdapter.ListViewHolder>() {

    class ListViewHolder(private var binding: ItemTrashBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: TrashList) {
            var currentValue = item.count

            binding.ivTrash.setImageResource(item.photo)
            binding.tvTitleTrash.text = item.title
            binding.tvTrashDesc.text = item.description
            binding.tvResult.text = currentValue.toString()

            binding.btnPlus.setOnClickListener {
                currentValue ++
                binding.tvResult.text = currentValue.toString()
            }

            binding.btnMin.setOnClickListener {
                if (currentValue > 0) {
                    currentValue--
                    binding.tvResult.text = currentValue.toString()
                }
            }
        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemTrashBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun getItemCount() : Int = listTrash.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val currentItem = listTrash[position]
        holder.bind(currentItem)
    }
}