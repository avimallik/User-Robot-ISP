package com.armavi_bsd.userrobotisp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.armavi_bsd.adapter.TransactionAdapter
import com.armavi_bsd.userrobotisp.databinding.ActivityTransactionHistoryBinding
import com.armavi_bsd.viewModel.TransactionViewModel

class TransactionHistory : AppCompatActivity() {

    private lateinit var binding: ActivityTransactionHistoryBinding
    private lateinit var transactionAdapter: TransactionAdapter
    private val billHistoryViewModel: TransactionViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_transaction_history)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_transaction_history)

        // Initialize RecyclerView and Adapter
        transactionAdapter = TransactionAdapter()
        binding.transactionRecycler.layoutManager = LinearLayoutManager(this)
        binding.transactionRecycler.adapter = transactionAdapter

        // Observe LiveData from ViewModel
        billHistoryViewModel.transactionListLiveData.observe(this, Observer { transactions ->
            transactionAdapter.submitList(transactions)
        })

        billHistoryViewModel.errorLiveData.observe(this, Observer { errorMessage ->
            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
        })

        // Fetch transaction history
        billHistoryViewModel.fetchTransactionHistory()

    }
}