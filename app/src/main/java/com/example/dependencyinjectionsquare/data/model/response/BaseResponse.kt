package com.example.dependencyinjectionsquare.data.model.response

import com.example.dependencyinjectionsquare.data.source.local.entity.ArticleEntity

class BaseResponse (

    val category: String,
    val data : ArrayList<ArticleResponse>
        )


