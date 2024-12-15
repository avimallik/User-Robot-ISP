package com.armavi_bsd.model

import java.util.Currency

data class PackageModel (
    val package_name:String,
    val net_speed:String,
    val bill_amount:String,
    val currency: String = "BDT ",
){
    val bdtCurrency: String
        get() = "BDT "
    val monthTag: String
        get() = "/mo"
}