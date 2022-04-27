package com.example.dependencyinjectionsquare.data.model.common

class ArticleDataModel(
    val title: String,

    val author: String,

    val description: String,

    val imageUrl: String,

    val readMoreUrl: String,

    val time: String,

    val url: String,

    val category: String,

    var isFav: Boolean = false
)