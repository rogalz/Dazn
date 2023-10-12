package com.example.dazn.data.repo

import com.example.dazn.data.api.DaznApiService
import com.example.dazn.domain.model.Event
import com.example.dazn.domain.model.Schedule
import com.example.dazn.domain.model.toDomain
import retrofit2.Response

class DanzRepository(
    private val api: DaznApiService
) {

    suspend fun getEvents(): Result<List<Event>> {
        return api.getEvents().mapToResult { it.toDomain() }
    }

    suspend fun getSchedules(): Result<List<Schedule>> {
        return api.getSchedules().mapToResult { it.toDomain() }
    }

    private fun <T, G> Response<List<T>>.mapToResult( map: (it: T) -> G): Result<List<G>> {
        return if (this.isSuccessful && !this.body().isNullOrEmpty())
            Result.success(this.body()!!.map { map.invoke(it) })
        else {
            Result.failure(Throwable(this.message()))
        }
    }
}
