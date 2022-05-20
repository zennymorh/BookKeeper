package com.zennymorh.bookkeeper.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.zennymorh.bookkeeper.model.Result
import javax.inject.Inject

class BookSource @Inject constructor(
    private val bookRepositoryImpl: BookRepositoryImpl
) : PagingSource<String, Result>() {

    override suspend fun load(params: LoadParams<String>): LoadResult<String, Result> {
        return try {
            val keyParams = params.key
            val page = keyParams?.toInt() ?: 1
            val bookListResponse = bookRepositoryImpl.getBookList(page)

            LoadResult.Page(
                data = bookListResponse.results,
                prevKey = bookListResponse.previous,
                nextKey = if (bookListResponse.next != null) page.plus(1).toString() else null
            )
        } catch (e: Exception) {
            //TODO Write the actual exception
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<String, Result>): String =
        state.anchorPosition?.let { state.closestItemToPosition(it)?.id }.toString()


}