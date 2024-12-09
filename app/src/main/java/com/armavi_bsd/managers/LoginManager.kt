import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
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
import com.armavi_bsd.userrobotisp.Dashboard
import com.armavi_bsd.userrobotisp.R
import com.armavi_bsd.utills.LoginJsonObjectKey
import com.armavi_bsd.utills.LoginPostKeys
import com.armavi_bsd.utills.LoginPrefKey
import com.armavi_bsd.utills.LoginTempResponseVar
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class LoginManager(
    private val context: Context,
    private val apiService: ApiService = ApiServiceImplementation()
) {
    lateinit var loginUrl: String
    var loginPostKeys = LoginPostKeys()
    var loginPrefKey = LoginPrefKey()
    var loginJsonObjectKey = LoginJsonObjectKey()
    lateinit var sharedPreferences: SharedPreferences
    lateinit var dashaBoardIntent: Intent
//    val editor: Editor = sharedPreferences.edit()
    lateinit var editor: Editor

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
                    loginPostKeys.keyPhoneNumber to phoneNumberParam,
                    loginPostKeys.keyUserID to userIDParam
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
//            Toast.makeText(context, response, Toast.LENGTH_SHORT).show()
            val jsonArray = JSONArray(response)
            for(i in 0 until jsonArray.length()){
                var jsonObject: JSONObject = jsonArray.getJSONObject(i)
//                loginResponseValue.tempAgId = jsonObject.getString(loginJsonObjectKey.responseAgID)
//                loginResponseValue.tempFiberColor = jsonObject.getString(loginJsonObjectKey.responseFiberColor)
//                Toast.makeText(context, loginResponseValue.tempFiberColor, Toast.LENGTH_SHORT).show()
                // Example of saving values from the jsonObject to SharedPreferences

                //Store login values from network

                //Store data in sharedpref
                sharedPreferences = context.getSharedPreferences(loginPrefKey.prefUserCredential, MODE_PRIVATE)
                editor = sharedPreferences.edit()

                editor.putString(loginPrefKey.prefAgId, jsonObject.getString(loginJsonObjectKey.responseAgID))
                editor.putString(loginPrefKey.prefCusId, jsonObject.getString(loginJsonObjectKey.responseCusID))
                editor.putString(loginPrefKey.prefAgName, jsonObject.getString(loginJsonObjectKey.responseAgName))
                editor.putString(loginPrefKey.prefAgEmail, jsonObject.getString(loginJsonObjectKey.responseAgEmail))
                editor.putString(loginPrefKey.prefAgOfficeAddress, jsonObject.getString(loginJsonObjectKey.responseAgOfficeAddress))
                editor.putString(loginPrefKey.prefAgMobileNo, jsonObject.getString(loginJsonObjectKey.responseAgMobileNo))
                editor.putString(loginPrefKey.prefInactiveDate, jsonObject.getString(loginJsonObjectKey.responseInactiveDate))
                editor.putString(loginPrefKey.prefMb, jsonObject.getString(loginJsonObjectKey.responseMb))
                editor.putString(loginPrefKey.prefTaka, jsonObject.getString(loginJsonObjectKey.responseTaka))
                editor.putString(loginPrefKey.prefPreviousDueAmount, jsonObject.getString(loginJsonObjectKey.responsePreviousDueAmount))
                editor.putString(loginPrefKey.prefBillCat, jsonObject.getString(loginJsonObjectKey.responseBillCat))
                editor.putString(loginPrefKey.prefConnectCharge, jsonObject.getString(loginJsonObjectKey.responseConnectCharge))
                editor.putString(loginPrefKey.prefDeviceType, jsonObject.getString(loginJsonObjectKey.responseDeviceType))
                editor.putString(loginPrefKey.prefFiberColor, jsonObject.getString(loginJsonObjectKey.responseFiberColor))
                editor.putString(loginPrefKey.prefFiberLatency, jsonObject.getString(loginJsonObjectKey.responseFiberLatency))
                editor.putString(loginPrefKey.prefSpilterType, jsonObject.getString(loginJsonObjectKey.responseSpilterType))
                editor.putString(loginPrefKey.prefLongParameter, jsonObject.getString(loginJsonObjectKey.responseLongParameter))
                editor.putString(loginPrefKey.prefHouse, jsonObject.getString(loginJsonObjectKey.responseHouse))
                editor.putString(loginPrefKey.prefRoad, jsonObject.getString(loginJsonObjectKey.responseRoad))
                editor.putString(loginPrefKey.prefZone, jsonObject.getString(loginJsonObjectKey.responseZone))
                editor.putString(loginPrefKey.prefBillingPersonId, jsonObject.getString(loginJsonObjectKey.responseBillingPersonID))
                editor.putString(loginPrefKey.prefZoneName, jsonObject.getString(loginJsonObjectKey.responseZoneName))
                editor.putString(loginPrefKey.prefThana, jsonObject.getString(loginJsonObjectKey.responseThana))
                editor.putString(loginPrefKey.prefIp, jsonObject.getString(loginJsonObjectKey.responseIp))
                editor.putString(loginPrefKey.prefAgStatus, jsonObject.getString(loginJsonObjectKey.responseAgStatus))
                editor.putString(loginPrefKey.prefMikrotikId, jsonObject.getString(loginJsonObjectKey.responseMikrotikID))
                editor.putString(loginPrefKey.prefPayStatus, jsonObject.getString(loginJsonObjectKey.responsePayStatus))
                editor.putString(loginPrefKey.prefDueStatus, jsonObject.getString(loginJsonObjectKey.responseDueStatus))
                editor.putString(loginPrefKey.prefBillStatus, jsonObject.getString(loginJsonObjectKey.responseBillStatus))
                editor.putString(loginPrefKey.prefMikrotikDisconnect, jsonObject.getString(loginJsonObjectKey.responseMikrotikDisconnect))
                editor.putString(loginPrefKey.prefHoldMoneyStatus, jsonObject.getString(loginJsonObjectKey.responseHoldMoneyStatus))
                editor.putString(loginPrefKey.prefBillDate, jsonObject.getString(loginJsonObjectKey.responseBillDate))
                editor.putString(loginPrefKey.prefDiffMonth, jsonObject.getString(loginJsonObjectKey.responseDiffMonth))
                editor.putString(loginPrefKey.prefInDiffMonth, jsonObject.getString(loginJsonObjectKey.responseInDiffMonth))
                editor.putString(loginPrefKey.prefConnectionDate, jsonObject.getString(loginJsonObjectKey.responseConnectionDate))
                editor.putString(loginPrefKey.prefCDate, jsonObject.getString(loginJsonObjectKey.responseCDate))
                editor.putString(loginPrefKey.prefEntryDate, jsonObject.getString(loginJsonObjectKey.responseEntryDate))

                editor.putBoolean(loginPrefKey.prefISLoggedIn, true)
                editor.putString(loginPrefKey.prefIsRecognition, "user_login")
                editor.apply()
                editor.commit()

                Toast.makeText(context, sharedPreferences.getString(loginPrefKey.prefAgName, ""), Toast.LENGTH_SHORT).show()

                dashaBoardIntent = Intent(context, Dashboard::class.java)
                context.startActivity(dashaBoardIntent)

            }
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