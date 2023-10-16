package com.example.dazn.domain.di

import com.example.dazn.domain.usecase.GetEventsUseCase
import com.example.dazn.domain.usecase.GetSchedulesUseCase
import org.koin.dsl.module

val domainModule = module {

    single { GetEventsUseCase(get()) }

    single { GetSchedulesUseCase(get()) }
}
