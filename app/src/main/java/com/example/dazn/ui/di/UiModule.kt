package com.example.dazn.ui.di

import com.example.dazn.ui.event.mapper.EventViewStateMapper
import com.example.dazn.ui.event.vm.EventsViewModel
import com.example.dazn.ui.schedule.vw.SchedulesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val uiModule = module {

    single { EventViewStateMapper() }

    viewModel {
        SchedulesViewModel(get())
    }

    viewModel {
        EventsViewModel(get(), get())
    }
}