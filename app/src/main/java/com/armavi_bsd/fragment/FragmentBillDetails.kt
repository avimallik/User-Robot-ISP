package com.armavi_bsd.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.armavi_bsd.model.BillAmountModel
import com.armavi_bsd.userrobotisp.R
import com.armavi_bsd.userrobotisp.databinding.FragmentBillAmountBinding
import com.armavi_bsd.userrobotisp.databinding.FragmentBillDetailsBinding
import com.armavi_bsd.userrobotisp.databinding.FragmentUserBioBinding
import com.armavi_bsd.utills.LoginPrefKey
import com.armavi_bsd.viewModel.BillAmountViewModel

class FragmentBillDetails : Fragment() {

    private val refreshBillAmountUI : Long = 1000
    val handlerBillAmount = Handler(Looper.getMainLooper())
    private val billAmountViewModel: BillAmountViewModel by viewModels()

    lateinit var binding: FragmentBillDetailsBinding
    lateinit var sharedPreferences: SharedPreferences
    var loginPrefKey = LoginPrefKey()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentBillDetailsBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_bill_details, container, false
        )

        binding.billAmountViewModel = billAmountViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        sharedPreferences = requireActivity().getSharedPreferences(loginPrefKey.prefUserCredential,
            Context.MODE_PRIVATE
        )

        binding.billMonthlyDetails.text = "৳"+sharedPreferences.getString(loginPrefKey.prefTaka, "")
        binding.billingDate.text = sharedPreferences.getString(loginPrefKey.prefCDate, "")
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
                binding.billDueDetails.text = "$billAmountModel.bill_amount"
            }
        }
        billAmountViewModel.billAmount.observe(viewLifecycleOwner, observer)

        return binding.root
    }
}