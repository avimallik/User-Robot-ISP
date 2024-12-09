package com.armavi_bsd.viewModel

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.armavi_bsd.apiService.ApiService
import com.armavi_bsd.model.TransactionModel
import com.armavi_bsd.retrofitClient.RetrofitClient
import com.armavi_bsd.utills.LoginPrefKey
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TransactionViewModel(application: Application) : AndroidViewModel(application) {

    val loginPrefKey = LoginPrefKey()
    private val _transactionListLiveData = MutableLiveData<List<TransactionModel>>()
    val transactionListLiveData: LiveData<List<TransactionModel>> = _transactionListLiveData

    private val _errorLiveData = MutableLiveData<String>()
    val errorLiveData: LiveData<String> = _errorLiveData

    private val sharedPreferences: SharedPreferences = application.getSharedPreferences(loginPrefKey.prefUserCredential, Context.MODE_PRIVATE)

    private val apiService: ApiService = RetrofitClient.apiService

    fun fetchTransactionHistory() {
        val clientID = sharedPreferences.getString(loginPrefKey.prefAgId, "default_client_id") ?: "default_client_id"
//        val clientID = "5"
        apiService.getTransactionHistory(clientID).enqueue(object :
            Callback<List<TransactionModel>> {
            override fun onResponse(call: Call<List<TransactionModel>>, response: Response<List<TransactionModel>>) {
                if (response.isSuccessful) {
                    _transactionListLiveData.value = response.body()
                } else {
                    _errorLiveData.value = "Error: ${response.message()}"
                }
            }

            override fun onFailure(call: Call<List<TransactionModel>>, t: Throwable) {
                _errorLiveData.value = "Failure: ${t.message}"
            }
        })
    }
}