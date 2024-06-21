package com.example.new_tourslate.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.new_tourslate.data.retrofit.ApiService
import com.example.new_tourslate.data.response.Data
import com.example.new_tourslate.data.response.DataItem
import com.example.new_tourslate.data.response.History

class HistoryRepository(private val apiService: ApiService) {
    fun getHistory(): LiveData<PagingData<DataItem>> {
        val pager = Pager(
            config = PagingConfig(
                pageSize = 5
            ),
            pagingSourceFactory = {
                HistoryPagingSource(apiService)
            }
        )
        Log.d("HistoryRepository", "Pager created: $pager")
        return pager.liveData
    }
}