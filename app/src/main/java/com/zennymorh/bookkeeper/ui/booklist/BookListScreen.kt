package com.zennymorh.bookkeeper.ui.booklist

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.zennymorh.bookkeeper.ErrorItem
import com.zennymorh.bookkeeper.LoadingItem
import com.zennymorh.bookkeeper.LoadingView
import com.zennymorh.bookkeeper.R
import com.zennymorh.bookkeeper.model.Result
import com.zennymorh.bookkeeper.navigation.BookListScreens
import kotlinx.coroutines.flow.Flow

@Composable
fun BookListScreen(bookListViewModel: BookListViewModel, navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Book Keeper") }
            )
        },
        content = {
            BookList(
                books = bookListViewModel.bookList,
                navController = navController,
                onClick =
                {bookId ->
                navController.navigate(BookListScreens.BookDetailScreen.route + "/$bookId")
                }
            )
        }
    )
}

@Composable
fun BookList(books: Flow<PagingData<Result>>, navController: NavController, onClick: (Int) -> Unit) {
    val lazyBookItems = books.collectAsLazyPagingItems()

    LazyColumn {
        items(lazyBookItems) { book ->
            BookItem(book = book!!){
                onClick(book.id)
            }
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
fun BookItem(book: Result, onClick: () -> Unit) {
    Card(shape = RoundedCornerShape(4.dp),
        border = BorderStroke(.2.dp, color = Color.LightGray),
        modifier = Modifier
            .clickable(enabled = true, onClick = onClick)
            .padding(vertical = 4.dp, horizontal = 8.dp)
            .animateContentSize(),
        elevation = 1.dp){
        Row(
            modifier = Modifier
                .padding(start = 16.dp, end = 4.dp, top = 4.dp, bottom = 4.dp)
                .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(
                modifier = Modifier,
                horizontalAlignment = Alignment.Start) {
                BookTitle(
                    book.title,
                    modifier = Modifier
                )
                BookAuthor(
                    book.authors[0].name,
                    modifier = Modifier
                )
            }

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
        shape = RoundedCornerShape(2.dp),
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
        modifier= modifier,
        text = title,
        maxLines = 2,
        style = MaterialTheme.typography.body1,
        overflow = TextOverflow.Ellipsis)
}

@Composable
fun BookAuthor(author: String, modifier: Modifier) {
    Text(
        modifier = modifier,
        text = author,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        style = MaterialTheme.typography.subtitle2
    )
}