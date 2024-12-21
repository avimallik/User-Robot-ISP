package com.armavi_bsd.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.armavi_bsd.model.PackageModel
import com.armavi_bsd.retrofitClient.RetrofitClient
import kotlinx.coroutines.launch

class PackageViewModel : ViewModel() {

    private val _packages = MutableLiveData<List<PackageModel>>()
    val packages: LiveData<List<PackageModel>> get() = _packages

    fun fetchPackages() {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.apiService.getPackages()
                if (response.isSuccessful) {
                    Log.d("PackageViewModel", "Fetched data: ${response.body()}")
                    _packages.value = response.body()
                } else {
                    Log.e("PackageViewModel", "Error: ${response.message()}")
                }
            } catch (e: Exception) {
                Log.e("PackageViewModel", "Error: ${e.message}")
            }
        }
    }
}


