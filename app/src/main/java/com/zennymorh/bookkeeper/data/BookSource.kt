package com.zennymorh.bookkeeper.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.zennymorh.bookkeeper.model.ApiResponse
import java.lang.Exception

class BookSource(private val bookRepository: BookRepository) : PagingSource<Int, ApiResponse.Result>(){
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ApiResponse.Result> {
        return try {
            val nextPage = params.key ?: 1
            val bookListResponse = bookRepository.getBookList()

            LoadResult.Page(
                data = bookListResponse.results,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = nextPage
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ApiResponse.Result>): Int? {
        TODO("Not yet implemented")
    }


}