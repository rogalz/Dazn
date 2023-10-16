package com.example.dazn.ui.screens.schedule.vw

import com.example.dazn.domain.usecase.GetSchedulesUseCase
import com.example.dazn.ui.screens.schedule.mapper.ScheduleViewStateMapper
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.jupiter.api.Test

@OptIn(ExperimentalCoroutinesApi::class)
class SchedulesViewModelTest {
    private val dispatcher: TestDispatcher = UnconfinedTestDispatcher()
    private val getSchedulesUseCase: GetSchedulesUseCase = mockk(relaxed = true) {
        coEvery { execute() } returns Result.success(
            listOf(
                mockk(relaxed = true),
                mockk(relaxed = true)
            )
        )
    }
    private val mapper: ScheduleViewStateMapper = mockk(relaxed = true)

    @Test
    fun `view models init starts flow`() {
        val tested = SchedulesViewModel(
            getSchedulesUseCase = getSchedulesUseCase,
            mapper = mapper,
            dispatcher = dispatcher
        )

        coVerify { getSchedulesUseCase.execute() }
        verify { mapper.map(any()) }
    }
}