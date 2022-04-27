package com.example.dependencyinjectionsquare.data.source.local

import com.example.dependencyinjectionsquare.data.model.response.ArticleResponse
import com.example.dependencyinjectionsquare.data.source.local.entity.ArticleEntity

object Categories {

    fun getAllCategory(): List<String> {
        val categories = ArrayList<String>()

        categories.add("all")
        categories.add("national")
        categories.add("business")
        categories.add("sports")
        categories.add("world")
        categories.add("politics")
        categories.add("technology")
        categories.add("startup")
        categories.add("entertainment")
        categories.add("science")
        categories.add("automobile")

        return categories

    }
}

fun ArticleResponse.toEntity(category: String) = ArticleEntity(title, author.toString(), description.toString(), imageUrl.toString(), readMoreUrl.toString(), time.toString(), url.toString(), category)
