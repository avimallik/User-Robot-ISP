package com.armavi_bsd

import android.app.Application
import android.content.Context
import android.content.Intent
import com.armavi_bsd.userrobotisp.TransactionHistory

class IntentRoute {
    fun intentTransactionHistory(context:Context){
        var transactionIntentTemp = Intent(context, TransactionHistory::class.java)
        context.startActivity(transactionIntentTemp)
    }
}