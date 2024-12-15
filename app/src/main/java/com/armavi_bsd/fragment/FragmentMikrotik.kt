package com.armavi_bsd.fragment

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
import com.armavi_bsd.model.MikrotikModel
import com.armavi_bsd.userrobotisp.R
import com.armavi_bsd.userrobotisp.databinding.FragmentMikrotikBinding
import com.armavi_bsd.viewModel.MikrotikViewModel

class FragmentMikrotik: Fragment() {

    lateinit var binding: FragmentMikrotikBinding

    private val refreshMikrotik : Long = 1000
    val handlerMikrotik = Handler(Looper.getMainLooper())

    //View model assignment
    private val mikrotikViewModel: MikrotikViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_mikrotik, container, false)

        // Bind the ViewModel
        binding.mikrotikViewModel = mikrotikViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        handlerMikrotik.postDelayed(object : Runnable{
            override fun run() {
                mikrotikViewModel.fetchMikrotikDetails()
                handlerMikrotik.postDelayed(this, refreshMikrotik)
            }

        }, refreshMikrotik)

        // Observe LiveData
        val observer = Observer<MikrotikModel> { mikrotikModel ->
            if (mikrotikModel != null) {
                // Handle the update here
                binding.mikrotikViewModel = mikrotikViewModel
            }
        }

        mikrotikViewModel.mikrotikDetails.observe(viewLifecycleOwner, observer)

        return binding.root
    }

}