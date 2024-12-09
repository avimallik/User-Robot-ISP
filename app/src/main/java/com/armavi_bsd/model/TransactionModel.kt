package com.armavi_bsd.model

data class TransactionModel(
    val history_id: Int,
    val entry_date: String,
    val acc_description: String,
    val acc_amount: String,
    val FullName: String,
    val UserName: String,
    val duePayment: Int
){
    val bdtCurrencyBangla: String
        get() = "৳"
}
