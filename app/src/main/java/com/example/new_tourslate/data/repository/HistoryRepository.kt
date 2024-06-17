//package com.example.new_tourslate.data.repository
//
//import androidx.lifecycle.LiveData
//import com.example.new_tourslate.data.retrofit.ApiService
//
//class HistoryRepository(
//
//) {
//    fun getStory(): LiveData<PagingData<ListStoryItem>> {
//        @OptIn(ExperimentalPagingApi::class)
//        return Pager(
//            config = PagingConfig(
////                pageSize = 5
//            )
//            remoteMediator = StoryRemoteMediator(storyDatabase, apiService),
//            pagingSourceFactory = {
//                storyDatabase.storyDao().getAllStory()
//            }
//        ).liveData
//    }
//
//    suspend fun getStoriesWithLocation(): AllStoryResponse {
//        return apiService.getStoriesWithLocation(location = 1)
//    }
//
//    suspend fun logout() {
//        userPreference.logout()
//    }
//
//    suspend fun getDetail(id: String): DetailStoryResponse {
//        return apiService.getDetailStory(id)
//    }
//
//    companion object {
//        @Volatile
//        private var instance: StoryRepository? = null
//        fun getInstance(
//            apiService: ApiService,
//            userPreference: UserPreference,
//            storyDatabase: StoryDatabase
//        ): StoryRepository =
//            instance ?: synchronized(this) {
//                instance ?: StoryRepository(apiService, userPreference, storyDatabase)
//            }.also { instance = it }
//    }