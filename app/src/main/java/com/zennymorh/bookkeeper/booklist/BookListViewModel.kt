package com.zennymorh.bookkeeper.booklist

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.zennymorh.bookkeeper.data.BookRepository
import com.zennymorh.bookkeeper.data.BookSource
import com.zennymorh.bookkeeper.model.ApiResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class BookListViewModel @Inject public constructor(val bookRepository: BookRepository) : ViewModel() {
    val books: Flow<PagingData<ApiResponse.Result>> = Pager(PagingConfig(pageSize = 20)) {
        BookSource(bookRepository)
    }.flow
}