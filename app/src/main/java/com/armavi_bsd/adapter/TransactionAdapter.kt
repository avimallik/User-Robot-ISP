package com.armavi_bsd.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.armavi_bsd.model.TransactionModel
import com.armavi_bsd.userrobotisp.databinding.ItemTransactionsBinding

class TransactionAdapter : RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder>() {

    private var transactionList: List<TransactionModel> = emptyList()

    fun submitList(list: List<TransactionModel>) {
        transactionList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val binding = ItemTransactionsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TransactionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        val transaction = transactionList[position]
        holder.bind(transaction)
    }

    override fun getItemCount(): Int {
        return transactionList.size
    }

    class TransactionViewHolder(private val binding: ItemTransactionsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(transaction: TransactionModel) {
            binding.transaction = transaction
            binding.executePendingBindings()
        }
    }
}