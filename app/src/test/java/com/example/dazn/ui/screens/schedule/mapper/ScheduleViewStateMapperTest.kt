package com.example.dazn.ui.screens.schedule.mapper

import com.example.dazn.domain.model.Schedule
import com.example.dazn.ui.components.ListItemViewState
import com.example.dazn.ui.screens.schedule.SchedulesScreenViewState
import com.example.dazn.ui.utils.TimeFormatter
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import java.time.ZoneId
import java.time.ZonedDateTime

class ScheduleViewStateMapperTest {

    private val timeFormatter: TimeFormatter = mockk(relaxed = true) {
        every { format(any()) } returns "OK"
    }
    private val tested = ScheduleViewStateMapper(timeFormatter)

    @Test
    fun map() {
        val zonedDateTime = ZonedDateTime.of(2023, 10, 16, 10, 17, 0, 0, ZoneId.systemDefault())
        val schedules = listOf(
            Schedule(
                id = "1",
                title = "First title",
                subtitle = "First subtitle",
                date = zonedDateTime,
                imageUrl = "first image",
            ), Schedule(
                id = "2",
                title = "2nd title",
                subtitle = "2nd subtitle",
                date = zonedDateTime.plusDays(3),
                imageUrl = "second image",
            )
        )

        val result = tested.map(schedules)


        val expectedList = listOf(
            ListItemViewState(
                imageUrl = "first image",
                title = "First title",
                description = "First subtitle",
                date = "OK",
            ), ListItemViewState(
                imageUrl = "second image",
                title = "2nd title",
                description = "2nd subtitle",
                date = "OK",
            )
        )
        val expected = SchedulesScreenViewState.Success(expectedList)
        assertThat(result).isEqualTo(expected)
    }
}