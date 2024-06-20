package com.example.new_tourslate.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.new_tourslate.data.database.HistoryDatabase
import com.example.new_tourslate.data.retrofit.ApiService
import com.example.new_tourslate.data.retrofit.Data

class HistoryRepository(private val historyDatabase: HistoryDatabase, private val apiService: ApiService) {
    fun getHistory(): LiveData<PagingData<Data>> {
        return Pager(
            config = PagingConfig(
                pageSize = 5
            ),
            pagingSourceFactory = {
                HistoryPagingSource(apiService)
            }
        ).liveData
    }
}