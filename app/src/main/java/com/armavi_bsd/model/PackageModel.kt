package com.armavi_bsd.model

import java.util.Currency

data class PackageModel (
    val id:String,
    val pack:String,
    val price:String,
    val description:String,
    val currency: String = "BDT ",
){
    val bdtCurrency: String
        get() = "BDT "
    val monthTag: String
        get() = "/mo"
}