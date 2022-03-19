package com.zennymorh.bookkeeper.model

data class Book(
    val title: String,
    val formats: String,
    val authors: List<Result.Author>
    )
