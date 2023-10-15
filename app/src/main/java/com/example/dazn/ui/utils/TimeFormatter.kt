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

        if (isToday(date, now)) {
            prefix = context.getString(R.string.today)
            format = DateTimeFormatter.ofPattern("HH:mm")
        } else if (isTomorrow(date, now)) {
            prefix = context.getString(R.string.tomorrow)
            format = DateTimeFormatter.ofPattern("HH:mm")
        } else if (wasYesterday(date, now)) {
            prefix = context.getString(R.string.yesterday)
            format = DateTimeFormatter.ofPattern("HH:mm")
        } else {
            prefix = ""
            format = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        }

        return "$prefix${date.format(format)}"

    }

    private fun isToday(zonedDateTime: ZonedDateTime, now: ZonedDateTime): Boolean {
        return now.toLocalDate() == zonedDateTime.toLocalDate()
    }

    private fun isTomorrow(zonedDateTime: ZonedDateTime, now: ZonedDateTime): Boolean {
        return now.plusDays(1L).toLocalDate() == zonedDateTime.toLocalDate()
    }

    private fun wasYesterday(zonedDateTime: ZonedDateTime, now: ZonedDateTime): Boolean {
        return now.minusDays(1L).toLocalDate() == zonedDateTime.toLocalDate()
    }
}