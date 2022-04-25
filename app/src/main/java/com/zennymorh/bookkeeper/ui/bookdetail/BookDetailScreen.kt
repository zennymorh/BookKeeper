package com.zennymorh.bookkeeper.ui.bookdetail

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.zennymorh.bookkeeper.navigation.BookListScreens

@Composable
fun BookDetailScreen(navController: NavController, bookId: Int?) {
//    val book = bookId?. let { it ->  }
    Toast.makeText(LocalContext.current, bookId.toString(), Toast.LENGTH_SHORT).show()
}