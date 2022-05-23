package com.zennymorh.bookkeeper

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.findNavController
import com.zennymorh.bookkeeper.data.BookRepositoryImpl
import com.zennymorh.bookkeeper.model.Result
import com.zennymorh.bookkeeper.navigation.Navigation
import com.zennymorh.bookkeeper.ui.booklist.BookItem
import com.zennymorh.bookkeeper.ui.booklist.BookListScreen
import com.zennymorh.bookkeeper.ui.booklist.BookListViewModel
import com.zennymorh.bookkeeper.ui.theme.BookKeeperTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            BookKeeperTheme {
                Navigation()
            }
        }
    }
}
