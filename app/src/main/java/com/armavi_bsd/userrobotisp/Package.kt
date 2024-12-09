package com.armavi_bsd.userrobotisp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.armavi_bsd.adapter.ItemAdapter
import com.armavi_bsd.adapter.PackageAdapter
import com.armavi_bsd.model.PackageModel
import com.armavi_bsd.userrobotisp.databinding.ActivityLoginBinding
import com.armavi_bsd.userrobotisp.databinding.ActivityPackageBinding
import com.armavi_bsd.utills.DashboardMenuUtills
import com.armavi_bsd.viewDefinition.ViewDefinationPackage
import com.armavi_bsd.viewModel.PackageViewModel

class Package : AppCompatActivity() {

    private lateinit var binding: ActivityPackageBinding
    private lateinit var viewModel: PackageViewModel
    private lateinit var adapter: PackageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_package)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_package)

        // Initialize ViewModel and RecyclerView
        viewModel = ViewModelProvider(this).get(PackageViewModel::class.java)
        adapter = PackageAdapter()

        binding.packageRecycler.layoutManager = GridLayoutManager(this, 2)
        binding.packageRecycler.adapter = adapter

        // Observe LiveData
        viewModel.packages.observe(this, Observer { packages ->
            if (packages != null) {
                adapter.submitList(packages)
            }
        })

        // Fetch data
        viewModel.fetchPackages()
    }

}
