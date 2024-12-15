package com.armavi_bsd.viewModel

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.armavi_bsd.model.MikrotikModel
import com.armavi_bsd.retrofitClient.RetrofitClient
import com.armavi_bsd.utills.LoginPrefKey
import retrofit2.Call
import retrofit2.Response

class MikrotikViewModel(application: Application): AndroidViewModel(application) {

    val loginPrefKey = LoginPrefKey()
    private val sharedPreferences: SharedPreferences = application.getSharedPreferences(loginPrefKey.prefUserCredential,
        Context.MODE_PRIVATE)

    private val _mikrotikDetails = MutableLiveData<MikrotikModel>()
    val mikrotikDetails: LiveData<MikrotikModel> get() = _mikrotikDetails
    fun fetchMikrotikDetails(){

        val mikID: String? = sharedPreferences.getString(loginPrefKey.prefMikrotikId, "")
        val ip: String? = sharedPreferences.getString(loginPrefKey.prefIp, "")

        RetrofitClient.apiService.getMikrotikDetails(mikID, ip).enqueue(object :
            retrofit2.Callback<MikrotikModel> {
            override fun onResponse(call: Call<MikrotikModel>, response: Response<MikrotikModel>) {
                if (response.isSuccessful) {
                    _mikrotikDetails.value = response.body()
                }
            }
            override fun onFailure(call: Call<MikrotikModel>, t: Throwable) {
                // Handle failure
            }
        })
    }

}