package com.example.dazn.domain.model

import com.example.dazn.data.api.model.ScheduleDto

data class Schedule(
    val id: String,
    val title: String,
    val subtitle: String,
    val date: String,
    val imageUrl: String,
)

fun ScheduleDto.toDomain(): Schedule {
    return Schedule(
        id = id ?: "",
        title = title ?: "",
        subtitle = subtitle ?: "",
        date = date ?: "",
        imageUrl = imageUrl ?: "",
    )
}
