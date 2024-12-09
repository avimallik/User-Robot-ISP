package com.armavi_bsd.fragment

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.armavi_bsd.userrobotisp.R
import com.armavi_bsd.userrobotisp.databinding.FragmentBillAmountBinding
import com.armavi_bsd.viewModel.BillAmountViewModel

class BillAmountFragment : Fragment() {

    private lateinit var binding: FragmentBillAmountBinding
    private lateinit var billAmountViewModel: BillAmountViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return binding.root
    }
}