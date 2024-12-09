package com.armavi_bsd.model


data class BillAmountModel(
    val bill_amount: Long
){
    val bdtCurrency: String
        get() = " BDT"

}
