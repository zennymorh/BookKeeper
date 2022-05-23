package com.zennymorh.bookkeeper.data

import androidx.lifecycle.LiveData
import com.zennymorh.bookkeeper.model.ApiResponse

class FakeBookRepository: BookRepository {

    override suspend fun getBookList(page: Int): ApiResponse {
        TODO("Not yet implemented")
    }
}