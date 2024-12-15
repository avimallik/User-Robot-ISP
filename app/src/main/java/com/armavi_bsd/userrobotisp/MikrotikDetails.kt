package com.armavi_bsd.userrobotisp

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.armavi_bsd.fragment.FragmentBillDetails
import com.armavi_bsd.fragment.FragmentMikrotik
import com.armavi_bsd.fragment.FragmentUserBio
import com.armavi_bsd.fragment.FragmentUserInfo
import com.armavi_bsd.intentRoute.IntentRoute
import com.armavi_bsd.userrobotisp.databinding.ActivityDashboardBinding
import com.armavi_bsd.utills.LoginPrefKey

class MikrotikDetails : AppCompatActivity() {

//    val intentRoute: IntentRoute = IntentRoute()
    lateinit var sharedPreferences: SharedPreferences
    var loginPrefKey = LoginPrefKey()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mikrotik_details)

        val fragmentMikrotik = FragmentMikrotik()
        val fragmentBillDetails = FragmentBillDetails()
        val fragmentUserBio = FragmentUserBio()
        val fragmentUserInfo = FragmentUserInfo()

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentMikrotik, fragmentMikrotik)  // R.id.fragmentContainer is the FrameLayout ID in the activity layout
            .commit()

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentUserBio, fragmentUserBio)  // R.id.fragmentContainer is the FrameLayout ID in the activity layout
            .commit()
    }
}