package com.example.dazn.data.api

import com.example.dazn.data.api.model.EventDto
import com.example.dazn.data.api.model.ScheduleDto
import retrofit2.Response
import retrofit2.http.GET

interface DaznApiService {

    @GET("getEvents")
    suspend fun getEvents(): Response<List<EventDto>>

    @GET("getSchedule")
    suspend fun getSchedules(): Response<List<ScheduleDto>>

}
