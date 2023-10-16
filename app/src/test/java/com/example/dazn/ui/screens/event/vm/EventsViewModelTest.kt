package com.example.dazn.ui.screens.event.vm

import com.example.dazn.domain.usecase.GetEventsUseCase
import com.example.dazn.ui.screens.event.EventsScreenViewState
import com.example.dazn.ui.screens.event.mapper.EventViewStateMapper
import com.google.common.truth.Truth.assertThat
import io.mockk.Called
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.jupiter.api.Test

@OptIn(ExperimentalCoroutinesApi::class)
class EventsViewModelTest {

    private val mapper: EventViewStateMapper = mockk(relaxed = true)
    private val dispatcher: TestDispatcher = UnconfinedTestDispatcher()
    private val getEventsUseCase: GetEventsUseCase = mockk(relaxed = true) {
        coEvery { execute() } returns Result.success(
            listOf(
                mockk(relaxed = true),
                mockk(relaxed = true)
            )
        )
    }

    private val tested =
        EventsViewModel(getEventsUseCase = getEventsUseCase, mapper = mapper, dispatcher)

    @Test
    fun `get events calls use case`() {
        tested.getEvents()

        coVerify { getEventsUseCase.execute() }
    }

    @Test
    fun `returns success view state when use case call succeeded`() {
        every { mapper.map(any()) } returns EventsScreenViewState.Success(listOf(mockk(relaxed = true)))

        tested.getEvents()

        val viewState = tested.uiState.value
        assertThat(viewState).isInstanceOf(EventsScreenViewState.Success::class.java)
        assertThat((viewState as EventsScreenViewState.Success).events.size).isEqualTo(1)
    }

    @Test
    fun `returns error view state when use case call failed`() {
        coEvery { getEventsUseCase.execute() } returns Result.failure(Throwable())

        tested.getEvents()

        val viewState = tested.uiState.value
        verify { mapper.map(any()) wasNot Called }
        assertThat(viewState).isInstanceOf(EventsScreenViewState.Error::class.java)
    }
}

