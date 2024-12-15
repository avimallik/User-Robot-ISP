package com.armavi_bsd.model

data class MikrotikModel(
    val serial: Int,
    val name: String,
    val password: String,
    val profile: String,
    val service: String,
    val local_address: String,
    val remote_address: String,
    val last_logged_out: String

)
