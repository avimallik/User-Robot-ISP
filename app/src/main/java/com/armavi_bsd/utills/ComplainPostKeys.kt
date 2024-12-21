package com.armavi_bsd.utills

import java.time.format.DateTimeFormatter

data class ComplainPostKeys(
    var key_customer_id: String = "customer_id",
    var key_note: String = "note",
    var key_details: String = "details",
    var key_status: String = "status",
    var key_solve_by: String = "solve_by",
    var key_complain_date: String = "complain_date"
)
