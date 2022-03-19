package com.zennymorh.bookkeeper.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ApiResponse(
    @SerializedName("count")
    val count: Int,
    @SerializedName("next")
    val next: String?,
    @SerializedName("previous")
    val previous: String?,
    @SerializedName("results")
    val results: List<Result>
) : Parcelable

@Parcelize
data class Result(
    @SerializedName("authors")
    val authors: List<Author>,
    @SerializedName("bookshelves")
    val bookshelves: List<String>,
    @SerializedName("copyright")
    val copyright: Boolean,
    @SerializedName("download_count")
    val downloadCount: Int,
    @SerializedName("formats")
    val formats: Formats,
    @SerializedName("id")
    val id: Int,
    @SerializedName("languages")
    val languages: List<String>,
    @SerializedName("media_type")
    val mediaType: String,
    @SerializedName("subjects")
    val subjects: List<String>,
    @SerializedName("title")
    val title: String,
    @SerializedName("translators")
    val translators: List<Translator>
    ) : Parcelable {
    @Parcelize
        data class Author(
            @SerializedName("birth_year")
            val birthYear: Int,
            @SerializedName("death_year")
            val deathYear: Int,
            @SerializedName("name")
            val name: String
        ): Parcelable

    @Parcelize
        data class Formats(
            @SerializedName("application/epub+zip")
            val applicationepubzip: String,
            @SerializedName("application/octet-stream")
            val applicationoctetStream: String,
            @SerializedName("application/rdf+xml")
            val applicationrdfxml: String,
            @SerializedName("application/x-mobipocket-ebook")
            val applicationxMobipocketEbook: String,
            @SerializedName("application/zip")
            val applicationzip: String,
            @SerializedName("image/jpeg")
            val imagejpeg: String,
            @SerializedName("text/html")
            val texthtml: String,
            @SerializedName("text/html; charset=iso-8859-1")
            val texthtmlCharsetiso88591: String,
            @SerializedName("text/html; charset=us-ascii")
            val texthtmlCharsetusAscii: String,
            @SerializedName("text/html; charset=utf-8")
            val texthtmlCharsetutf8: String,
            @SerializedName("text/plain")
            val textplain: String,
            @SerializedName("text/plain; charset=us-ascii")
            val textplainCharsetusAscii: String,
            @SerializedName("text/plain; charset=utf-8")
            val textplainCharsetutf8: String
        ): Parcelable

    @Parcelize
        data class Translator(
            @SerializedName("birth_year")
            val birthYear: Int?,
            @SerializedName("death_year")
            val deathYear: Int,
            @SerializedName("name")
            val name: String
        ): Parcelable
    }