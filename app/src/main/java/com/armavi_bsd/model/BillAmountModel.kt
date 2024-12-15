package com.armavi_bsd.model


data class BillAmountModel(
    val bill_amount: Long,
    val total_pay: Long
){
    val bdtCurrency: String
        get() = " BDT"

    val bdtCurrencyBangla: String
        get() = "৳"

}
