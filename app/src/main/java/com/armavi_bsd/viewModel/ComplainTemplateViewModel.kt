package com.armavi_bsd.viewModel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.armavi_bsd.apiService.ApiService
import com.armavi_bsd.model.ComplainTemplateModel
import com.armavi_bsd.retrofitClient.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ComplainTemplateViewModel: ViewModel() {

    private val _templates = MutableLiveData<List<ComplainTemplateModel>>()
    val templates: LiveData<List<ComplainTemplateModel>> = _templates
    val apiService: ApiService = RetrofitClient.apiService

    fun loadTemplates(){
//        val apiService:ApiService = RetrofitClient.apiService
        apiService.getComplainTemplate().enqueue(object : Callback<List<ComplainTemplateModel>> {
            override fun onResponse(call: Call<List<ComplainTemplateModel>>,
                                    response: Response<List<ComplainTemplateModel>>) {
                if (response.isSuccessful) {
                    _templates.postValue(response.body())
                } else {
                    _templates.postValue(emptyList())
                }
            }

            override fun onFailure(call: Call<List<ComplainTemplateModel>>, t: Throwable) {
                _templates.postValue(emptyList())
            }
        })
    }

}