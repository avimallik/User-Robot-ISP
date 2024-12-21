package com.armavi_bsd.model

data class ComplainModel(
    val complain_id: String,
    val details: String,
    val note: String,
    val status: String,
    val employee_name: String,
    val entry_date: String,
    val ip: String,
    val solve_date: String,
    val complain_date: String
){
    val employeeNameTitle: String
        get() = "Assigned By : "
    val ipTitle: String
        get() = "IP : "
    val complainDateTitle: String
        get() = "Complain date : "
}
