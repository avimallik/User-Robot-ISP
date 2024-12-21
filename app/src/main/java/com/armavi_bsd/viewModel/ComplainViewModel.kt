package com.armavi_bsd.viewModel

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.armavi_bsd.apiService.ApiService
import com.armavi_bsd.model.ComplainModel
import com.armavi_bsd.model.TransactionModel
import com.armavi_bsd.retrofitClient.RetrofitClient
import com.armavi_bsd.utills.LoginPrefKey
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ComplainViewModel(application: Application) : AndroidViewModel(application) {
    var loginPrefKey = LoginPrefKey()
    private val _complainLiveData = MutableLiveData<List<ComplainModel>>()
    val complainLiveData: LiveData<List<ComplainModel>> = _complainLiveData

    private val _errorLiveData = MutableLiveData<String>()
    val errorLiveData: LiveData<String> = _errorLiveData

    private val sharedPreferences: SharedPreferences = application.getSharedPreferences(loginPrefKey.prefUserCredential, Context.MODE_PRIVATE)
    private val apiService: ApiService = RetrofitClient.apiService

    fun fetchComplainView(){
        val clientID = sharedPreferences.getString(loginPrefKey.prefAgId,
            "default_client_id") ?: "default_client_id"

        apiService.getComplainView(clientID).enqueue(object :
            Callback<List<ComplainModel>> {
            override fun onResponse(call: Call<List<ComplainModel>>, response: Response<List<ComplainModel>>) {
                if (response.isSuccessful) {
                    _complainLiveData.value = response.body()
                } else {
                    _errorLiveData.value = "Error: ${response.message()}"
                }
            }

            override fun onFailure(call: Call<List<ComplainModel>>, t: Throwable) {
                _errorLiveData.value = "Failure: ${t.message}"
            }
        })

    }
}