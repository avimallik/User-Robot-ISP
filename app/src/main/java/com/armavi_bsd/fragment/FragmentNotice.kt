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
import com.armavi_bsd.model.BillAmountModel
import com.armavi_bsd.model.NoticeModel
import com.armavi_bsd.userrobotisp.R
import com.armavi_bsd.userrobotisp.databinding.FragmentBillAmountBinding
import com.armavi_bsd.userrobotisp.databinding.FragmentNoticeBinding
import com.armavi_bsd.viewModel.BillAmountViewModel
import com.armavi_bsd.viewModel.NoticeViewModel

class FragmentNotice: Fragment() {

    private val refreshNoticeUI : Long = 1000
    val handlerNotice = Handler(Looper.getMainLooper())

    //View model assignment
    private val noticeViewModel: NoticeViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentNoticeBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_notice, container, false
        )

        // Bind the ViewModel
        binding.noticeViewModel = noticeViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        /// Fetch the data
        handlerNotice.postDelayed(object : Runnable{
            override fun run() {
                noticeViewModel.fetchNotice()
                handlerNotice.postDelayed(this, refreshNoticeUI)
            }

        }, refreshNoticeUI)

        // Observe LiveData
        val observer = Observer<NoticeModel> { noticeModel ->
            if (noticeModel != null) {

                // Handle the update here
                binding.noticeViewModel = noticeViewModel

                if(noticeModel.notice_body == ""){
                    binding.noticeContainer.visibility = View.GONE
                }else{
                    binding.noticeContainer.visibility = View.VISIBLE
                }
            }
        }

        noticeViewModel.noticeDetails.observe(viewLifecycleOwner, observer)
        return binding.root
    }
}