package com.zennymorh.bookkeeper.network

import com.zennymorh.bookkeeper.model.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("books")
    suspend fun getBookList(@Query("page") page: Int): ApiResponse
}