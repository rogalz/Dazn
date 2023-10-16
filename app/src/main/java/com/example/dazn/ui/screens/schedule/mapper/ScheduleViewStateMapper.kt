package com.example.dazn.ui.screens.schedule.mapper

import com.example.dazn.domain.model.Schedule
import com.example.dazn.ui.components.ListItemViewState
import com.example.dazn.ui.screens.schedule.SchedulesScreenViewState
import com.example.dazn.ui.utils.TimeFormatter

class ScheduleViewStateMapper(
    private val timeFormatter: TimeFormatter
) {

    fun map(events: List<Schedule>): SchedulesScreenViewState.Success {

        val schedulesViewStateList = events.sortedBy { it.date }.map { event ->
            ListItemViewState(
                event.imageUrl,
                event.title,
                event.subtitle,
                timeFormatter.format(event.date),
            )
        }

        return SchedulesScreenViewState.Success(schedulesViewStateList)
    }
}
