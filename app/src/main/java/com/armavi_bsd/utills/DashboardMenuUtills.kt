package com.armavi_bsd.utills

import android.content.Context
import com.armavi_bsd.model.ItemMenu
import com.armavi_bsd.userrobotisp.R

object DashboardMenuUtills {
    fun getDashBoardMenuUtill (menuItemContext: Context) : List<ItemMenu>{
        return listOf(
            ItemMenu(menuItemContext.getString(R.string.menu_profile_info), R.drawable.face_ico),
            ItemMenu(menuItemContext.getString(R.string.menu_package), R.drawable.package_ico),
            ItemMenu(menuItemContext.getString(R.string.menu_payment_history), R.drawable.payment_history_ico),
            ItemMenu(menuItemContext.getString(R.string.menu_microtik), R.drawable.router_ico),
            ItemMenu(menuItemContext.getString(R.string.menu_complain), R.drawable.complain_ico)
        )
    }
}