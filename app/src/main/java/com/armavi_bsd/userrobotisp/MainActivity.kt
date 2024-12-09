package com.armavi_bsd.userrobotisp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.armavi_bsd.fragment.FragmentBillAmount
import com.armavi_bsd.fragment.FragmentNotice
import com.armavi_bsd.fragment.FragmentUserBio

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        // Set up the toolbar
//        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
//        setSupportActionBar(toolbar)
        if (savedInstanceState == null) {

            val fragmentBillAmount = FragmentBillAmount()
            val fragmentNotice = FragmentNotice()
            val fragmentUserBio = FragmentUserBio()

            // Add the fragment dynamically
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentBillAmount, fragmentBillAmount)  // R.id.fragmentContainer is the FrameLayout ID in the activity layout
                .commit()

            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentNotice, fragmentNotice)
                .commit()

            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentUserBio, fragmentUserBio)
                .commit()

        }
    }
}