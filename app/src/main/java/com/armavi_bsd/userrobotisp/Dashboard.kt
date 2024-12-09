package com.armavi_bsd.userrobotisp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.armavi_bsd.IntentRoute
import com.armavi_bsd.adapter.ItemAdapter
import com.armavi_bsd.fragment.FragmentBillAmount
import com.armavi_bsd.fragment.FragmentNotice
import com.armavi_bsd.fragment.FragmentUserBio
import com.armavi_bsd.utills.DashboardMenuUtills
import com.armavi_bsd.viewDefinition.ViewDefinitionDashboard

class Dashboard : AppCompatActivity() {

    val intentRoute: IntentRoute = IntentRoute()

    private lateinit var viewDefinitionDashboard: ViewDefinitionDashboard
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

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
            }
        }

        //Attached recyclerView with adapter
        viewDefinitionDashboard.menuRecycler.adapter = adapterDashboardMenu
    }
}