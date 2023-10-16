package com.example.dazn.domain.usecase

import com.example.dazn.data.repo.DanzRepository
import com.example.dazn.domain.model.Schedule

class GetSchedulesUseCase(
    private val repo: DanzRepository
) {

    suspend fun execute(): Result<List<Schedule>> = Result.runCatching {
        return repo.getSchedules()
    }
}