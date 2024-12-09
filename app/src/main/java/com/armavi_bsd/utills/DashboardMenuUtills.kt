package com.armavi_bsd.model

import com.armavi_bsd.userrobotisp.R

object DashboardMenuUtills {
    fun getDashBoardMenuUtill () : List<ItemMenu>{
        return listOf(
            ItemMenu("Title-1", R.drawable.notice_ico),
            ItemMenu("Title-2", R.drawable.payment_ico)
        )
    }
}