package com.armavi_bsd.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.armavi_bsd.model.ComplainModel
import com.armavi_bsd.userrobotisp.databinding.ItemComplainViewBinding

class ComplainAdapter : RecyclerView.Adapter<ComplainAdapter.ComplainViewHolder>() {

    private var complainList: List<ComplainModel> = emptyList()

    fun submitList(list: List<ComplainModel>) {
        complainList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ComplainAdapter.ComplainViewHolder {
        val binding = ItemComplainViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ComplainAdapter.ComplainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ComplainAdapter.ComplainViewHolder, position: Int) {
        val complain = complainList[position]
        holder.bind(complain)
    }

    override fun getItemCount(): Int {
        return  complainList.size
    }

    class ComplainViewHolder(private val binding: ItemComplainViewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(complain: ComplainModel) {
            binding.complain = complain
            binding.executePendingBindings()
            if(complain.status == "3"){
                binding.itemComplainStatus.setText("Solved")
            }else if(complain.status == "1"){
                binding.itemComplainStatus.setText("Pending")
            }else if(complain.status == "2"){
                binding.itemComplainStatus.setText("Reviewed")
            }else if(complain.status == "4"){
                binding.itemComplainStatus.setText("Not Solved")
            }
//            binding.itemComplainStatus.setText("ss")
        }
    }
}