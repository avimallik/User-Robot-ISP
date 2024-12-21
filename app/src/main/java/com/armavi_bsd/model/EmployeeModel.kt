package com.armavi_bsd.model

data class EmployeeModel(
    val id: String,
    val employee_id: String,
    val employee_name: String,
    val employee_mobile_no: String,
    val employee_address: String,
    val employee_email: String?,
    val employee_national_id: String?,
    val designation: String?,
    val joining_date: String,
    val employee_status: String,
    val entry_by: String,
    val entry_date: String,
    val update_by: String
)
