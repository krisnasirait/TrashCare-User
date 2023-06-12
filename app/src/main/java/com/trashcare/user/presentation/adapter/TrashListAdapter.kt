package com.trashcare.user.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.trashcare.user.databinding.ItemTrashBinding
import android.content.Intent
import com.trashcare.user.data.model.trashlist.TrashList
import com.trashcare.user.presentation.activity.DetailTrashActivity

class TrashListAdapter(
    private val listTrash: List<TrashList>,
    //bikin interface baru buat detect perubahan amount sampahnya
    private val trashAmountChangeListener: OnTrashAmountChangeListener,
) : RecyclerView.Adapter<TrashListAdapter.ListViewHolder>() {

    //biasain view holder selalu inner class, bukan class biasa
    inner class ListViewHolder(private var binding: ItemTrashBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: TrashList, trashAmountChangeListener: OnTrashAmountChangeListener) {
            var currentValue = item.count
            val mContext = binding.root.context

            binding.ivTrash.setImageResource(item.photo)
            binding.tvTitleTrash.text = item.title
            binding.tvTrashDesc.text = item.description
            binding.tvResult.text = currentValue.toString()

            binding.btnPlus.setOnClickListener {
                currentValue ++
                item.count = currentValue
                binding.tvResult.text = currentValue.toString()
                trashAmountChangeListener.onTrashAmountChange(getTotalTrashCount())
            }

            binding.btnMin.setOnClickListener {
                if (currentValue > 0) {
                    currentValue--
                    item.count = currentValue
                    binding.tvResult.text = currentValue.toString()
                    trashAmountChangeListener.onTrashAmountChange(getTotalTrashCount())
                }
            }

            binding.btnDetailTrash.setOnClickListener {
                val intent = Intent(mContext, DetailTrashActivity::class.java)
                intent.putExtra(DetailTrashActivity.EXTRA_TITLE, item.title)
                intent.putExtra(DetailTrashActivity.EXTRA_DESC, item.description)
                intent.putExtra(DetailTrashActivity.EXTRA_PHOTO, item.photo)
                mContext.startActivity(intent)
            }
        }
    }

    //count sum nya biasa
    private fun getTotalTrashCount(): Int {
        return listTrash.sumOf { it.count }
    }

    interface OnTrashAmountChangeListener {
        fun onTrashAmountChange(totalAmount: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemTrashBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun getItemCount() : Int = listTrash.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val currentItem = listTrash[position]
        holder.bind(currentItem, trashAmountChangeListener)
    }
}