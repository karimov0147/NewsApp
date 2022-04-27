package com.example.dependencyinjectionsquare.presenter

import androidx.lifecycle.LiveData
import com.example.dependencyinjectionsquare.data.model.common.ArticleDataModel

interface ArticleViewModel {
    val loadingLiveData : LiveData<Boolean>
    val backLiveData : LiveData<Unit>
    val articlesLiveData : LiveData<List<ArticleDataModel>>

    fun backPressed()

    fun loadArticlesByCategory(category : String)

    fun updateArticle(title : String , state : Boolean)
}