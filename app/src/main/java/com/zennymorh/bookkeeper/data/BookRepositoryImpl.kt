package com.zennymorh.bookkeeper.data

import com.zennymorh.bookkeeper.model.ApiResponse
import com.zennymorh.bookkeeper.network.ApiService
import javax.inject.Inject

class BookRepositoryImpl @Inject constructor(
    private val apiService: ApiService): BookRepository {

    override suspend fun getBookList(page: Int): ApiResponse{
        return apiService.getBookList(page)
    }

}
