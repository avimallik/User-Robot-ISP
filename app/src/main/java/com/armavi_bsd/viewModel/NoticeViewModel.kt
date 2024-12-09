package com.armavi_bsd.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.armavi_bsd.model.BillAmountModel
import com.armavi_bsd.model.NoticeModel
import com.armavi_bsd.retrofitClient.RetrofitClient
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class NoticeViewModel: ViewModel() {
    private val _notice = MutableLiveData<NoticeModel>()
    val noticeDetails: LiveData<NoticeModel> get() = _notice

    fun fetchNotice() {
        RetrofitClient.apiService.getNotice().enqueue(object :
            retrofit2.Callback<NoticeModel> {
            override fun onResponse(call: Call<NoticeModel>, response: Response<NoticeModel>) {
                if (response.isSuccessful) {
                    _notice.value = response.body()
                }
            }
            override fun onFailure(call: Call<NoticeModel>, t: Throwable) {
                // Handle failure
            }
        })
    }

}