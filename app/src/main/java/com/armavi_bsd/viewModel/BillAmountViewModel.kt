package com.armavi_bsd.viewModel
import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.armavi_bsd.apiService.ApiService
import com.armavi_bsd.model.BillAmountModel
import com.armavi_bsd.model.NoticeModel
import com.armavi_bsd.model.PackageModel
import com.armavi_bsd.retrofitClient.RetrofitClient
import com.armavi_bsd.utills.LoginPrefKey
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class BillAmountViewModel(application: Application) : AndroidViewModel(application) {

    val loginPrefKey = LoginPrefKey()
    private val sharedPreferences: SharedPreferences = application.getSharedPreferences(loginPrefKey.prefUserCredential,
        Context.MODE_PRIVATE)

    private val _billAmount = MutableLiveData<BillAmountModel>()
    val billAmount: LiveData<BillAmountModel> get() = _billAmount

    fun fetchBillAmount(){
        val clientID = sharedPreferences.getString(loginPrefKey.prefAgId, "default_client_id") ?: "default_client_id"
        RetrofitClient.apiService.getBillAmount(clientID).enqueue(object :
            retrofit2.Callback<BillAmountModel> {
            override fun onResponse(call: Call<BillAmountModel>, response: Response<BillAmountModel>) {
                if (response.isSuccessful) {
                    _billAmount.value = response.body()
                }
            }
            override fun onFailure(call: Call<BillAmountModel>, t: Throwable) {
                // Handle failure
            }
        })
    }
}