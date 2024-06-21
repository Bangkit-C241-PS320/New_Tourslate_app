package com.example.new_tourslate.di

import android.content.Context
import com.example.new_tourslate.data.repository.HistoryRepository
import com.example.new_tourslate.data.retrofit.ApiConfig

object Injection {
    fun provideRepository(context: Context): HistoryRepository {
        val apiService = ApiConfig.getApiService()
        return HistoryRepository(apiService)
    }
}