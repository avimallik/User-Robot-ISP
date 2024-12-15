package com.armavi_bsd.userrobotisp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.armavi_bsd.fragment.FragmentBillDetails
import com.armavi_bsd.fragment.FragmentUserBio
import com.armavi_bsd.fragment.FragmentUserInfo

class Payment : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

        val fragmentBillDetails = FragmentBillDetails()
        val fragmentUserBio = FragmentUserBio()
        val fragmentUserInfo = FragmentUserInfo()

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentUserBio, fragmentUserBio)  // R.id.fragmentContainer is the FrameLayout ID in the activity layout
            .commit()

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentBillDetails, fragmentBillDetails)  // R.id.fragmentContainer is the FrameLayout ID in the activity layout
            .commit()
    }
}