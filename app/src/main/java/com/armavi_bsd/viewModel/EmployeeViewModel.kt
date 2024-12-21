package com.armavi_bsd.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.armavi_bsd.apiService.ApiService
import com.armavi_bsd.model.ComplainTemplateModel
import com.armavi_bsd.model.EmployeeModel
import com.armavi_bsd.retrofitClient.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EmployeeViewModel: ViewModel() {
    private val _employee = MutableLiveData<List<EmployeeModel>>()
    val employee: LiveData<List<EmployeeModel>> = _employee
    val apiService: ApiService = RetrofitClient.apiService

    fun loadEmployee(){
//        val apiService:ApiService = RetrofitClient.apiService
        apiService.getEmployee().enqueue(object : Callback<List<EmployeeModel>> {
            override fun onResponse(call: Call<List<EmployeeModel>>,
                                    response: Response<List<EmployeeModel>>
            ) {
                if (response.isSuccessful) {
                    _employee.postValue(response.body())
                } else {
                    _employee.postValue(emptyList())
                }
            }

            override fun onFailure(call: Call<List<EmployeeModel>>, t: Throwable) {
                _employee.postValue(emptyList())
            }
        })
    }


}