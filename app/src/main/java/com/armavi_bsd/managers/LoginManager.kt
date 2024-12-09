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
import com.armavi_bsd.utills.LoginKeys
import org.json.JSONException

class LoginOperation(
    private val context: Context,
    private val apiService: ApiService = ApiServiceImplementation()
) {
    lateinit var loginUrl: String
    var loginKeys = LoginKeys()

    fun operationLogin(phoneNumberParam: String, userIDParam: String) {
        loginUrl = RetrofitClient.BASE_URL + apiService.loginEndpoint()

        val stringRequest: StringRequest = object : StringRequest(
            Method.POST, loginUrl,
            Response.Listener<String> { response ->
                handleResponse(response)
            },
            Response.ErrorListener { error ->
                handleError(error)
            }) {
            override fun getParams(): Map<String, String> {
                return hashMapOf(
                    loginKeys.keyPhoneNumber to phoneNumberParam,
                    loginKeys.keyUserID to userIDParam
                )
            }
        }.apply {
            retryPolicy = DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
            )
        }

        Volley.newRequestQueue(context).add(stringRequest)
    }

    private fun handleResponse(response: String) {
        Log.e("Login Response", response)
        try {
            // Example processing logic
            Toast.makeText(context, response, Toast.LENGTH_SHORT).show()
        } catch (e: JSONException) {
            Log.d("JSON parsing error", e.toString())
            e.printStackTrace()
        }
    }

    private fun handleError(error: VolleyError) {
        Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show()
        Log.d("Volley network error", error.toString())
    }
}