package com.example.dazn.domain.usecase

import com.example.dazn.data.repo.DanzRepository
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test

class GetSchedulesUseCaseTest {
    private val repo: DanzRepository = mockk(relaxed = true)
    private val tested = GetSchedulesUseCase(repo)

    @Test
    fun `execute method calls repo`() = runTest {

        tested.execute()

        coVerify { repo.getSchedules() }
    }

    @Test
    fun `when repo call fails use case returns fail`() = runTest {
        coEvery { repo.getSchedules() } throws Throwable()

        val result = tested.execute()

        assertThat(result.isFailure).isTrue()
    }
}