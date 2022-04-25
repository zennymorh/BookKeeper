package com.zennymorh.bookkeeper.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.zennymorh.bookkeeper.ui.bookdetail.BookDetailScreen
import com.zennymorh.bookkeeper.ui.booklist.BookListScreen
import com.zennymorh.bookkeeper.ui.booklist.BookListViewModel

@Composable
fun Navigation(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = BookListScreens.BookListScreen.route){
        composable(route = BookListScreens.BookListScreen.route) {
            val viewModel = hiltViewModel<BookListViewModel>(it)
            BookListScreen(bookListViewModel = viewModel,navController = navController)
        }

        composable(route = BookListScreens.BookDetailScreen.route +"/{bookId}", arguments =
        listOf(navArgument(name = "bookId") {
            type = NavType.IntType })
        ) { entry ->
            BookDetailScreen(navController = navController, entry.arguments?.getInt("bookId"))
        }
    }
}