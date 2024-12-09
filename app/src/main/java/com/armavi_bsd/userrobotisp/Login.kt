package com.armavi_bsd.userrobotisp

import LoginManager
import android.content.Intent
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.armavi_bsd.userrobotisp.databinding.ActivityLoginBinding
import com.armavi_bsd.utills.LoginPrefKey
import okhttp3.internal.cache.DiskLruCache


class Login : AppCompatActivity() {

    lateinit var dashBoardActivity: Intent

    //Shared pref variable
    lateinit var sharedPreferences: SharedPreferences
    lateinit var editor: Editor

    private lateinit var binding: ActivityLoginBinding
    lateinit var loginManager:LoginManager

    var loginPrefKey = LoginPrefKey()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        dashBoardActivity = Intent(applicationContext, Dashboard::class.java)

        //Page state check
        sharedPreferences = getSharedPreferences(loginPrefKey.prefUserCredential, MODE_PRIVATE)
        var isLoggedIn: Boolean = sharedPreferences.getBoolean(loginPrefKey.prefISLoggedIn, false)

        if(isLoggedIn){
            val dashBoardIntent = Intent(applicationContext, Dashboard::class.java)
            startActivity(dashBoardIntent)
        }

//        loginButton = findViewById(R.id.loginButton)
//        loginCustomerIDInput = findViewById<EditText>(R.id.loginCustomerIDInput)
//        loginPhoneNumberInput = findViewById<EditText>(R.id.loginPhoneNumberInput)

        val ag_mobile_no = "01757751407"
        val cus_id = "MIT00005"

        loginManager = LoginManager(this)

        binding.loginButton.setOnClickListener {
//            Toast.makeText(applicationContext,textCheck, Toast.LENGTH_SHORT).show()
            val tempPhoneNumber = binding.loginPhoneNumberInput.text.toString()
            val tempCustomerIDInput = binding.loginCustomerIDInput.text.toString()
            loginManager.operationLogin(tempPhoneNumber, tempCustomerIDInput)
        }
    }


}