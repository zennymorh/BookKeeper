package com.zennymorh.bookkeeper.data

import com.zennymorh.bookkeeper.network.ApiService
import javax.inject.Inject

class BookRepository @Inject constructor(private val apiService: ApiService, private val page: Int) {
    suspend fun getBookList() = apiService.getBookList(page)
}