package com.example.dazn.domain.model

import com.example.dazn.data.api.model.ScheduleDto
import java.time.ZonedDateTime

data class Schedule(
    val id: String,
    val title: String,
    val subtitle: String,
    val date: ZonedDateTime,
    val imageUrl: String,
)

fun ScheduleDto.toDomain(): Schedule {
    return Schedule(
        id = id ?: "",
        title = title ?: "",
        subtitle = subtitle ?: "",
        date = ZonedDateTime.parse(date ?: ""),
        imageUrl = imageUrl ?: "",
    )
}
