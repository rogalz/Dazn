package com.example.dazn.ui.utils

import java.time.ZonedDateTime

fun ZonedDateTime.isToday(now: ZonedDateTime): Boolean {
    return now.toLocalDate() == this.toLocalDate()
}

fun ZonedDateTime.isTomorrow(now: ZonedDateTime): Boolean {
    return now.plusDays(1L).toLocalDate() == this.toLocalDate()
}

fun ZonedDateTime.wasYesterday(now: ZonedDateTime): Boolean {
    return now.minusDays(1L).toLocalDate() == this.toLocalDate()
}