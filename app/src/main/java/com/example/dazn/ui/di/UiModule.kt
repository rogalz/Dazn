package com.example.dazn.ui.di

import com.example.dazn.ui.screens.event.mapper.EventViewStateMapper
import com.example.dazn.ui.screens.event.vm.EventsViewModel
import com.example.dazn.ui.screens.schedule.mapper.ScheduleViewStateMapper
import com.example.dazn.ui.screens.schedule.vw.SchedulesViewModel
import com.example.dazn.ui.utils.TimeFormatter
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val uiModule = module {

    single { EventViewStateMapper(get()) }

    single { ScheduleViewStateMapper(get()) }

    single { TimeFormatter(androidContext()) }


    viewModel {
        SchedulesViewModel(get(), get())
    }

    viewModel {
        EventsViewModel(get(), get())
    }
}