package com.armavi_bsd.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.armavi_bsd.model.PackageModel
import com.armavi_bsd.userrobotisp.R
import com.armavi_bsd.userrobotisp.databinding.ItemPackageBinding

class PackageAdapter: RecyclerView.Adapter<PackageAdapter.PackageViewHolder>() {

    private var packages = listOf<PackageModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PackageViewHolder {
        val binding = ItemPackageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PackageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PackageViewHolder, position: Int) {
        holder.bind(packages[position])
    }

    override fun getItemCount(): Int {
        return packages.size
    }

    fun submitList(newPackages: List<PackageModel>) {
        packages = newPackages
        notifyDataSetChanged()
    }

    inner class PackageViewHolder(private val binding: ItemPackageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(packageModel: PackageModel) {
            binding.packageModel = packageModel
            binding.executePendingBindings()
        }
    }
}

