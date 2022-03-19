package com.zennymorh.bookkeeper.booklist

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import com.zennymorh.bookkeeper.ErrorItem
import com.zennymorh.bookkeeper.LoadingItem
import com.zennymorh.bookkeeper.LoadingView
import com.zennymorh.bookkeeper.R
import com.zennymorh.bookkeeper.model.Result
import kotlinx.coroutines.flow.Flow

@Composable
fun BookListScreen(bookListViewModel: BookListViewModel) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Book Keeper") }
            )
        },
        content = {
            BookList(books = bookListViewModel.bookList)
        }
    )
}

@Composable
fun BookList(books: Flow<PagingData<Result>>) {
    val lazyBookItems = books.collectAsLazyPagingItems()

    LazyColumn {
        items(lazyBookItems) { book ->
            BookItem(book = book!!)
        }

        lazyBookItems.apply {
            when {
                loadState.refresh is LoadState.Loading -> {

                    item { LoadingView(modifier = Modifier.fillParentMaxSize()) }
                }
                loadState.append is LoadState.Loading -> {
                    item { LoadingItem() }
                }
                loadState.refresh is LoadState.Error -> {
                    val e = lazyBookItems.loadState.refresh as LoadState.Error
                    item {
                        ErrorItem(
                            message = e.error.localizedMessage!!,
                            modifier = Modifier.fillParentMaxSize(),
                            onClickRetry = { retry() }
                        )
                    }
                }
                loadState.append is LoadState.Error -> {
                    val e = lazyBookItems.loadState.append as LoadState.Error
                    item {
                        ErrorItem(
                            message = e.error.localizedMessage!!,
                            onClickRetry = { retry() }
                        )
                    }
                }
            }
        }
    }


}

@Composable
fun BookItem(book: Result) {
    Card(shape = RoundedCornerShape(8.dp),
    elevation = 2.dp,
    modifier = Modifier) {
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
                    .size(90.dp))
        }
    }

}

@Composable
fun BookImage(
    imageUrl: String,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(4.dp),
        content = {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imageUrl)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.ic_photo),
                contentDescription = "Book Cover",
                modifier = modifier,
                contentScale = ContentScale.FillBounds
            )
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