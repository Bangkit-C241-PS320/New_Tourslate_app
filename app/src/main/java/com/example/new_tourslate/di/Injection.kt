//package com.example.new_tourslate.di
//
//import android.content.Context
//import com.example.new_tourslate.data.retrofit.ApiConfig
//
//object Injection {
//    fun provideRepository(context: Context): QuoteRepository {
//        val database = QuoteDatabase.getDatabase(context)
//        val apiService = ApiConfig.getApiService()
//        return QuoteRepository(database, apiService)
//    }
//}