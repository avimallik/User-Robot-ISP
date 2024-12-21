package com.armavi_bsd

import android.app.Application
import android.content.Context
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class DateAndTime {
    fun dateAndTime(context: Context): String {
        val currentTime = Calendar.getInstance().time
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        val formattedDate = dateFormat.format(currentTime)
        return formattedDate
    }
}