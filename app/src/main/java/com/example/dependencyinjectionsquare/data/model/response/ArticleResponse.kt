package com.example.dependencyinjectionsquare.data.model.response

import com.google.gson.annotations.SerializedName

class ArticleResponse (
    val title: String,

    val author: String?,

    @SerializedName("content")
    val description: String?,

    @SerializedName("imageUrl")
    val imageUrl: String?,

    @SerializedName("readMoreUrl")
    val readMoreUrl: String?,

    @SerializedName("date")
    val time: String?,

    val url: String?

    )