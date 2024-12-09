package com.armavi_bsd.apiService

import com.armavi_bsd.model.PackageModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET


interface PackageApi {
    @GET("b/QZ9N") // Endpoint from JSONKeeper URL
    suspend fun getPackages(): Response<List<PackageModel>>

    

}