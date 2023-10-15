package com.example.dazn.ui.screens.event.mapper

import com.example.dazn.domain.model.Event
import com.example.dazn.ui.components.ListItemViewState
import com.example.dazn.ui.screens.event.EventsScreenViewState
import com.example.dazn.ui.utils.TimeFormatter

class EventViewStateMapper(
    private val timeFormatter: TimeFormatter
) {

    fun map(events: List<Event>): EventsScreenViewState.Success {

        val eventsViewStateList = events.sortedBy {it.date }.map { event ->
            ListItemViewState(
                event.imageUrl,
                event.title,
                event.subtitle,
                timeFormatter.format(event.date),
                event.videoUrl
            )
        }

        return EventsScreenViewState.Success(eventsViewStateList)
    }
}
