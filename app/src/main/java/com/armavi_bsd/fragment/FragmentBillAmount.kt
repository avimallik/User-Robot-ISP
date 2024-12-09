package com.armavi_bsd.fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.armavi_bsd.model.BillAmountModel
import com.armavi_bsd.model.NoticeModel
import com.armavi_bsd.retrofitClient.RetrofitClient
import com.armavi_bsd.userrobotisp.R
import com.armavi_bsd.userrobotisp.databinding.FragmentBillAmountBinding
import com.armavi_bsd.viewModel.BillAmountViewModel


class FragmentBillAmount : Fragment() {

    private val refreshBillAmountUI : Long = 1000
    val handlerBillAmount = Handler(Looper.getMainLooper())
    private val billAmountViewModel: BillAmountViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentBillAmountBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_bill_amount, container, false
        )
        binding.billAmountViewModel = billAmountViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        /// Fetch the data
        handlerBillAmount.postDelayed(object : Runnable{
            override fun run() {
                billAmountViewModel.fetchBillAmount()
                handlerBillAmount.postDelayed(this, refreshBillAmountUI)
            }

        }, refreshBillAmountUI)

        // Observe LiveData
        val observer = Observer<BillAmountModel> { billAmountModel ->
            if (billAmountModel != null) {
                // Handle the update here
                binding.billAmountViewModel = billAmountViewModel
                binding.billAmountDisp.text = "$billAmountModel.bill_amount"
            }
        }

        billAmountViewModel.billAmount.observe(viewLifecycleOwner, observer)
        return binding.root
    }
}