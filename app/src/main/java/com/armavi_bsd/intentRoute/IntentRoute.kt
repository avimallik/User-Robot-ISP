package com.armavi_bsd.intentRoute

import android.content.Context
import android.content.Intent
import com.armavi_bsd.userrobotisp.Complain
import com.armavi_bsd.userrobotisp.ComplainView
import com.armavi_bsd.userrobotisp.Login
import com.armavi_bsd.userrobotisp.MikrotikDetails
import com.armavi_bsd.userrobotisp.PackageCustomer
import com.armavi_bsd.userrobotisp.Payment
import com.armavi_bsd.userrobotisp.Profile
import com.armavi_bsd.userrobotisp.TransactionHistory

class IntentRoute {
    fun intentTransactionHistory(context:Context){
        var transactionIntentTemp = Intent(context, TransactionHistory::class.java)
        context.startActivity(transactionIntentTemp)
    }
    fun intentProfile(context: Context){
        var profileIntentTemp = Intent(context, Profile::class.java)
        context.startActivity(profileIntentTemp)
    }
    fun intentPackage(context: Context){
        var packageIntentTemp = Intent(context, PackageCustomer::class.java)
        context.startActivity(packageIntentTemp)
    }
    fun intentLogin(context: Context){
        var loginIntentTemp = Intent(context, Login::class.java)
        context.startActivity(loginIntentTemp)
    }
    fun intentPayment(context: Context){
        var paymentIntentTemp = Intent(context, Payment::class.java)
        context.startActivity(paymentIntentTemp)
    }

    fun intentMikrotik(context: Context){
        var mikrotikIntentTemp = Intent(context, MikrotikDetails::class.java)
        context.startActivity(mikrotikIntentTemp)
    }

    fun intentComplainPost(context: Context){
        var complainPostIntentTemp = Intent(context, Complain::class.java)
        context.startActivity(complainPostIntentTemp)
    }

    fun intentComplainView(context: Context){
        var complaViewIntentTemp = Intent(context, ComplainView::class.java)
        context.startActivity(complaViewIntentTemp)
    }
}