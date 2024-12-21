package com.armavi_bsd.userrobotisp

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.armavi_bsd.intentRoute.IntentRoute
import com.armavi_bsd.adapter.ItemAdapter
import com.armavi_bsd.fragment.FragmentBillAmount
import com.armavi_bsd.fragment.FragmentNotice
import com.armavi_bsd.fragment.FragmentUserBio
import com.armavi_bsd.userrobotisp.databinding.ActivityDashboardBinding
import com.armavi_bsd.utills.DashboardMenuUtills
import com.armavi_bsd.utills.LoginPrefKey
import com.armavi_bsd.viewDefinition.ViewDefinitionDashboard

class Dashboard : AppCompatActivity() {

    lateinit var binding: ActivityDashboardBinding

    val intentRoute: IntentRoute = IntentRoute()
    lateinit var sharedPreferences: SharedPreferences
    var loginPrefKey = LoginPrefKey()

    private lateinit var viewDefinitionDashboard: ViewDefinitionDashboard
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_dashboard)

        binding = DataBindingUtil.setContentView(this,
            R.layout.activity_dashboard)

        sharedPreferences = getSharedPreferences(loginPrefKey.prefUserCredential, MODE_PRIVATE)

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

        //Rootview assign
        viewDefinitionDashboard = ViewDefinitionDashboard(this)

        //Menu item list
        val itemList = DashboardMenuUtills.getDashBoardMenuUtill(this);

        //Dashboard items align on the Grid representation
        //Gridlayout manager for dashboard items
        val gridLayoutManager = GridLayoutManager(this, 2)

        //Assignment of recyclerView form ViewDefinition Class
        viewDefinitionDashboard.menuRecycler.layoutManager = gridLayoutManager

        //Item click return position of each item
        val adapterDashboardMenu = ItemAdapter(itemList) {clickedItem, position ->
            if(position == 2){
                intentRoute.intentTransactionHistory(this)
            }else if(position == 0){
                intentRoute.intentProfile(this)
            }else if(position == 1){
                intentRoute.intentPackage(this)
            }else if(position == 3){
                intentRoute.intentMikrotik(this)
            }else if(position == 4){
                intentRoute.intentComplainView(this)
            }
        }

        binding.toolbarLogoutBtn.setOnClickListener {
            Toast.makeText(applicationContext, "Logged out!",Toast.LENGTH_SHORT).show()
            sharedPreferences.edit().clear().commit()
            intentRoute.intentLogin(this)
            finish()
        }
        //Attached recyclerView with adapter
        viewDefinitionDashboard.menuRecycler.adapter = adapterDashboardMenu
    }
}