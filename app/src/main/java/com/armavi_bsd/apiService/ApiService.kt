package com.armavi_bsd.apiService

import com.armavi_bsd.model.BillAmountModel
import com.armavi_bsd.model.MikrotikModel
import com.armavi_bsd.model.NoticeModel
import com.armavi_bsd.model.PackageModel
import com.armavi_bsd.model.TransactionModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService{
    @GET("client-packages.php") // Endpoint from JSONKeeper URL
    suspend fun getPackages(): Response<List<PackageModel>>
    @GET("client-due-amount.php")
    fun getBillAmount(@Query("clientID") clientID: String): Call<BillAmountModel>
    @GET("client-bill-history.php")
    fun getTransactionHistory(@Query("clientID") clientID: String): Call<List<TransactionModel>>
    @GET("client-mikrotik-view.php")
    fun getMikrotikDetails(
        @Query("mik_id") mikID: String?,
        @Query("ip") ip: String?
    ): Call<MikrotikModel>
    @GET("client-notice.php")
    fun getNotice(): Call<NoticeModel>
    fun loginEndpoint(): String {
        return "client-login.php"
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

    override fun getMikrotikDetails(mikID: String?, ip: String?): Call<MikrotikModel> {
        TODO("Not yet implemented")
    }


//    override fun getBillAmount(): Call<BillAmountModel> {
//        TODO("Not yet implemented")
//    }

    override fun getNotice(): Call<NoticeModel> {
        TODO("Not yet implemented")
    }


}