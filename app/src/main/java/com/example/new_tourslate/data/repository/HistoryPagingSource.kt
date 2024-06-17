//package com.example.new_tourslate.data.repository
//
//import androidx.paging.PagingSource
//import androidx.paging.PagingState
//import com.example.new_tourslate.data.retrofit.ApiService
//
//class HistoryPagingSource(private val apiService: ApiService) : PagingSource<Int, QuoteResponseItem>() {
//
//    private companion object {
//        const val INITIAL_PAGE_INDEX = 1
//    }
//
//    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, QuoteResponseItem> {
//        return try {
//            val page = params.key ?: INITIAL_PAGE_INDEX
//            val responseData = apiService.getQuote(page, params.loadSize)
//
//            LoadResult.Page(
//                data = responseData,
//                prevKey = if (page == 1) null else page - 1,
//                nextKey = if (responseData.isNullOrEmpty()) null else page + 1
//            )
//        } catch (exception: Exception) {
//            return LoadResult.Error(exception)
//        }
//    }
//
//    override fun getRefreshKey(state: PagingState<Int, QuoteResponseItem>): Int? {
//        return state.anchorPosition?.let { anchorPosition ->
//            val anchorPage = state.closestPageToPosition(anchorPosition)
//            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
//        }
//    }
//}