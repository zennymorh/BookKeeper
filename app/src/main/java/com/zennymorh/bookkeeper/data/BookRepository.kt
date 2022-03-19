package com.zennymorh.bookkeeper.data

import androidx.paging.PagingData
import com.zennymorh.bookkeeper.model.ApiResponse
import com.zennymorh.bookkeeper.network.ApiService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface BookRepository {
    suspend fun getBookList(page: Int): ApiResponse
}