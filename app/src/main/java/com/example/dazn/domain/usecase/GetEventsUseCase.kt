package com.example.dazn.domain.usecase

import com.example.dazn.data.repo.DanzRepository
import com.example.dazn.domain.model.Event

class GetEventsUseCase(
    private val repo: DanzRepository
) {

    suspend fun execute(): Result<List<Event>> = Result.runCatching {

        val response = repo.getEvents()
        return Result.success(emptyList())
    }
}