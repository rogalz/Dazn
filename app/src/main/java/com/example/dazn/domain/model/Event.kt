package com.example.dazn.domain.model

import com.example.dazn.data.api.model.EventDto
import java.time.ZonedDateTime

data class Event(
    val id: String,
    val title: String,
    val subtitle: String,
    val date: ZonedDateTime,
    val imageUrl: String,
    val videoUrl: String,
)

fun EventDto.toDomain(): Event {
    return Event(
        id = id ?: "",
        title = title ?: "",
        subtitle = subtitle ?: "",
        date = ZonedDateTime.parse(date ?: ""),
        imageUrl = imageUrl ?: "",
        videoUrl = videoUrl ?: "",
    )
}
