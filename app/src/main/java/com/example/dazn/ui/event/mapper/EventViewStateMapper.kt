package com.example.dazn.ui.event.mapper

import com.example.dazn.domain.model.Event
import com.example.dazn.ui.components.ListItemViewState
import com.example.dazn.ui.event.EventsScreenViewState

class EventViewStateMapper {

    fun map(events: List<Event>): EventsScreenViewState.Success {



        val eventsViewStateList = events.map { event ->
            ListItemViewState(
                event.imageUrl,
                event.title,
                event.subtitle,
                event.date,
                event.videoUrl
            )
        }
        return EventsScreenViewState.Success(eventsViewStateList)
    }
}

/*

- ordered by date in ascending order
* Today, 10:30; Yesterday 10:30; 10.02.2019
 */