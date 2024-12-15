package com.armavi_bsd.userrobotisp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.armavi_bsd.fragment.FragmentBillDetails
import com.armavi_bsd.fragment.FragmentUserBio
import com.armavi_bsd.fragment.FragmentUserInfo

class Profile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val fragmentBillDetails = FragmentBillDetails()
        val fragmentUserBio = FragmentUserBio()
        val fragmentUserInfo = FragmentUserInfo()

        // Add the fragment dynamically
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentBillDetails, fragmentBillDetails)  // R.id.fragmentContainer is the FrameLayout ID in the activity layout
            .commit()

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentUserBio, fragmentUserBio)  // R.id.fragmentContainer is the FrameLayout ID in the activity layout
            .commit()

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentUserInfo, fragmentUserInfo)  // R.id.fragmentContainer is the FrameLayout ID in the activity layout
            .commit()

    }
}