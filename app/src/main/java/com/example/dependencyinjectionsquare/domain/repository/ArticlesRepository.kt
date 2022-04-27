package com.example.dependencyinjectionsquare.domain.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.dependencyinjectionsquare.data.model.common.ArticleDataModel
import com.example.dependencyinjectionsquare.data.model.response.BaseResponse

interface ArticlesRepository {

    //get data by category
    fun loadArticlesByCategory(category: String) : LiveData<List<ArticleDataModel>>

    //get category list
    fun getCategoryList() : List<String>

    //Loading func
    fun loadingState() : LiveData<Boolean>

    //get favorite list
    fun getFavoriteArticles() : LiveData<List<ArticleDataModel>>

    //change state article
    fun changeStateArticle(title: String, state: Boolean)

    fun loadArticlesByCategoryFromCache(category: String): LiveData<List<ArticleDataModel>>

}