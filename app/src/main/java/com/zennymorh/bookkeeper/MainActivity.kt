package com.zennymorh.bookkeeper

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.zennymorh.bookkeeper.navigation.Navigation
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
