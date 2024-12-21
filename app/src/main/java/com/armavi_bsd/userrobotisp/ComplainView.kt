package com.armavi_bsd.userrobotisp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.armavi_bsd.adapter.ComplainAdapter
import com.armavi_bsd.intentRoute.IntentRoute
import com.armavi_bsd.userrobotisp.databinding.ActivityComplainViewBinding
import com.armavi_bsd.viewModel.ComplainViewModel

class ComplainView : AppCompatActivity() {

    val intentRoute: IntentRoute = IntentRoute()

    private lateinit var binding: ActivityComplainViewBinding
    private  lateinit var complainAdapter: ComplainAdapter
    private val complainViewModel: ComplainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_complain_view)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_complain_view)

        complainAdapter = ComplainAdapter()
        binding.complainRecycler.layoutManager = LinearLayoutManager(this)
        binding.complainRecycler.adapter = complainAdapter

        complainViewModel.complainLiveData.observe(this, Observer { complains ->
            complainAdapter.submitList(complains)
        })

        complainViewModel.errorLiveData.observe(this, Observer { errorMessage ->
            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
        })
        complainViewModel.fetchComplainView()

        binding.complainPostIntentBtn.setOnClickListener {
            intentRoute.intentComplainPost(this)
        }
    }
}