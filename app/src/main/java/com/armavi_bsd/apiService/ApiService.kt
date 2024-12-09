package com.armavi_bsd.apiService

import android.content.SharedPreferences
import com.armavi_bsd.model.BillAmountModel
import com.armavi_bsd.model.LoginResponse
import com.armavi_bsd.model.NoticeModel
import com.armavi_bsd.model.PackageModel
import com.armavi_bsd.model.TransactionModel
import com.armavi_bsd.utills.EndpointAccessor
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query


interface ApiService{
    @GET("b/QZ9N") // Endpoint from JSONKeeper URL
    suspend fun getPackages(): Response<List<PackageModel>>
    @GET("/mitisp/rest_api_client/client-due-amount.php")
    fun getBillAmount(@Query("clientID") clientID: String): Call<BillAmountModel>
    @GET("/mitisp/rest_api_client/client-bill-history.php")
    fun getTransactionHistory(@Query("clientID") clientID: String): Call<List<TransactionModel>>
    @GET("b/J8UZ")
    fun getNotice(): Call<NoticeModel>
    fun loginEndpoint(): String {
        return "/mitisp/rest_api_client/client-login.php"
    }
}

class ApiServiceImplementation: ApiService{
    override suspend fun getPackages(): Response<List<PackageModel>> {
        TODO("Not yet implemented")
    }

    override fun getBillAmount(clientID: String): Call<BillAmountModel> {
        TODO("Not yet implemented")
    }

    override fun getTransactionHistory(clientID: String): Call<List<TransactionModel>> {
        TODO("Not yet implemented")
    }


//    override fun getBillAmount(): Call<BillAmountModel> {
//        TODO("Not yet implemented")
//    }

    override fun getNotice(): Call<NoticeModel> {
        TODO("Not yet implemented")
    }


}