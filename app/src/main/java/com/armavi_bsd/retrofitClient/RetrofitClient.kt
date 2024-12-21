package com.armavi_bsd.retrofitClient

import com.armavi_bsd.apiService.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    //Live URL
    const val BASE_URL = "https://mit.robotispsoft.net/rest_api_client/"

    //Local URL
    //const val BASE_URL = "http://192.168.0.126/mitisp/rest_api_client/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService: ApiService = retrofit.create(ApiService::class.java)
}


