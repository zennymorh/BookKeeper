package com.zennymorh.bookkeeper.ui.booklist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.zennymorh.bookkeeper.data.FakeBookRepository
import com.zennymorh.bookkeeper.util.MainCoroutineRule
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule

class BookListViewModelTest {
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: BookListViewModel
    private lateinit var bookRepository: FakeBookRepository

    // Executes each task synchronously
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUpViewModel() {
        //Initialize repo
    }

}