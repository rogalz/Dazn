package com.example.dazn.ui.utils

import android.content.Context
import com.example.dazn.R
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

class TimeFormatter(private val context: Context) {

    fun format(date: ZonedDateTime): String {
        val now = ZonedDateTime.now()
        val prefix: String
        val format: DateTimeFormatter

        if (date.isToday(now)) {
            prefix = context.getString(R.string.today)
            format = DateTimeFormatter.ofPattern("HH:mm")
        } else if (date.isTomorrow(now)) {
            prefix = context.getString(R.string.tomorrow)
            format = DateTimeFormatter.ofPattern("HH:mm")
        } else if (date.wasYesterday(now)) {
            prefix = context.getString(R.string.yesterday)
            format = DateTimeFormatter.ofPattern("HH:mm")
        } else {
            prefix = ""
            format = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        }

        return "$prefix${date.format(format)}"

    }
}