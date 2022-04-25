package com.zennymorh.bookkeeper.navigation

sealed class BookListScreens(val route: String) {
    object BookListScreen : BookListScreens("book_list_screen")
    object BookDetailScreen : BookListScreens("book_detail_screen")
}
