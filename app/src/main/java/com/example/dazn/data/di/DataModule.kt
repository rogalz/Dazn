package com.example.dazn.data.di

import com.example.dazn.data.api.DaznApiService
import com.example.dazn.data.repo.DanzRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val dataModule = module {
    val BASE_URL = "https://us-central1-dazn-sandbox.cloudfunctions.net/"


    single {
        val interceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        OkHttpClient.Builder().addInterceptor(interceptor).build()
    }

    single {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(get())
            .build()

        retrofit.create(DaznApiService::class.java)
    }

    single { DanzRepository(get()) }

}