package com.zennymorh.bookkeeper.booklist

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.zennymorh.bookkeeper.model.ApiResponse
import dev.chrisbanes.accompanist.coil.CoilImage
import kotlinx.coroutines.flow.Flow

@Composable
fun BookListScreen(bookListViewModel: BookListViewModel) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "PopularMovies") }
            )
        },
        content = {
            BookList(books = bookListViewModel.books)
        }
    )
}

@Composable
fun BookList(books: Flow<PagingData<ApiResponse.Result>>) {
    val lazyBookItems: LazyPagingItems<ApiResponse.Result> = books.collectAsLazyPagingItems()

    LazyColumn {
        items(lazyBookItems) { book ->
            BookItem(book = book!!)
        }
    }

    lazyBookItems.apply {
        when {
//            loadState.refresh is LoadState.Loading -> {
//                item { LoadingView(modifier = Modifier.fillParentMaxSize()) }
//            }
//            loadState.append is LoadState.Loading -> {
//                item { LoadingItem() }
//            }
//            loadState.refresh is LoadState.Error -> {
//                val e = lazyBookItems.loadState.refresh as LoadState.Error
//                item {
//                    ErrorItem(
//                        message = e.error.localizedMessage!!,
//                        modifier = Modifier.fillParentMaxSize(),
//                        onClickRetry = { retry() }
//                    )
//                }
//            }
//            loadState.append is LoadState.Error -> {
//                val e = lazyBookItems.loadState.append as LoadState.Error
//                item {
//                    ErrorItem(
//                        message = e.error.localizedMessage!!,
//                        onClickRetry = { retry() }
//                    )
//                }
//            }
        }
    }
}

@Composable
fun BookItem(book: ApiResponse.Result) {
    Row(
        modifier = Modifier
            .padding(start = 16.dp, top = 16.dp, end = 16.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        BookTitle(
            book.title,
            modifier = Modifier.weight(1f)
        )
        BookImage(
            imageUrl = book.formats.imagejpeg,
            modifier = Modifier
                .padding(start = 16.dp)
                .size(90.dp))
    }
}

@Composable
fun BookImage(
    imageUrl: String,
    modifier: Modifier = Modifier
) {
    CoilImage(
        data = imageUrl,
        modifier = modifier,
        fadeIn = true,
        contentScale = ContentScale.Crop,
        loading = {
//            Image(vectorResource(id = R.drawable.ic_photo), alpha = 0.45f)
        },
        error = {
//            Image(vectorResource(id = R.drawable.ic_broken_image), alpha = 0.45f)
        }
    )
}

@Composable
fun BookTitle(title: String, modifier: Modifier) {
    Text(
        modifier = modifier,
        text = title,
        maxLines = 2,
        style = MaterialTheme.typography.h6,
        overflow = TextOverflow.Ellipsis)
}