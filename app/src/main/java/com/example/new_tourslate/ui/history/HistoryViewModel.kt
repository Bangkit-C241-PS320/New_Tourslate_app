//package com.example.new_tourslate.ui.history
//
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.example.new_tourslate.data.repository.HistoryRepository
//import kotlinx.coroutines.launch
//
//class HistoryViewModel(private val historyRepository: HistoryRepository): ViewModel() {
//    val story: LiveData<PagingData<ListStoryItem>> =
//        storyRepository.getStory().cachedIn(viewModelScope)
//
//    fun logOut() {
//        viewModelScope.launch {
//            storyRepository.logout()
//        }
//    }
//}