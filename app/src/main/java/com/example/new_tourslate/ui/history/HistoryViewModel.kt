package com.example.new_tourslate.ui.history

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.new_tourslate.data.repository.HistoryRepository
import com.example.new_tourslate.data.retrofit.Data
import com.example.new_tourslate.di.Injection

class HistoryViewModel(private val historyRepository: HistoryRepository): ViewModel() {

    val history: LiveData<PagingData<Data>> =
        historyRepository.getHistory().cachedIn(viewModelScope)
}

class ViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HistoryViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HistoryViewModel(Injection.provideRepository(context)) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}