package com.zennymorh.bookkeeper.booklist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.zennymorh.bookkeeper.data.BookRepositoryImpl
import com.zennymorh.bookkeeper.data.BookSource
import com.zennymorh.bookkeeper.model.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookListViewModel @Inject constructor(private val bookRepositoryImpl: BookRepositoryImpl) : ViewModel() {

    val bookList: Flow<PagingData<Result>> = Pager(PagingConfig(pageSize = 20)) {
        BookSource(bookRepositoryImpl)
    }.flow

}