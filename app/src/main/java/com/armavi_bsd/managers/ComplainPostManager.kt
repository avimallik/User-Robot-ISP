package com.armavi_bsd.managers

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.armavi_bsd.apiService.ApiService
import com.armavi_bsd.apiService.ApiServiceImplementation
import com.armavi_bsd.retrofitClient.RetrofitClient
import com.armavi_bsd.utills.ComplainPostKeys
import com.armavi_bsd.utills.LoginPrefKey


class ComplainPostManager(
    private val context: Context,
    private val apiService: ApiService = ApiServiceImplementation()
) {
    lateinit var complainPostUrl: String

    var loginPrefKey = LoginPrefKey()
    var complainPostKeys = ComplainPostKeys()

    fun complainPost(customerIdParam: String,
                     detailsParam: String,
                     noteParam: String,
                     solveByParam: String,
                     complainDateTimeParam: String, ){

        complainPostUrl = RetrofitClient.BASE_URL + apiService.complainPostEndpoint()
        val queue = Volley.newRequestQueue(context)

        val stringRequest: StringRequest = object : StringRequest(
            Method.POST, complainPostUrl,
            Response.Listener<String> { response ->
                handleResponse(response)
            },
            Response.ErrorListener { error ->
                handleError(error)
            }) {
            override fun getParams(): Map<String, String> {
                return hashMapOf(
                    complainPostKeys.key_customer_id to customerIdParam,
                    complainPostKeys.key_details to detailsParam,
                    complainPostKeys.key_note to noteParam,
                    complainPostKeys.key_solve_by to solveByParam,
                    complainPostKeys.key_complain_date to complainDateTimeParam
                )
            }
        }.apply {
            retryPolicy = DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
            )
        }
        queue.add(stringRequest)
//        Volley.newRequestQueue(context).add(stringRequest)
    }

    private fun handleResponse(response: String) {
        Log.e("Login Response", response)
        Toast.makeText(context, response, Toast.LENGTH_SHORT).show()
    }

    private fun handleError(error: VolleyError) {
        Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show()
        Log.d("Volley network error", error.toString())
    }

}