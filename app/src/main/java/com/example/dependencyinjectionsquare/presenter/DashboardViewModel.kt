package com.example.dependencyinjectionsquare.presenter

import androidx.lifecycle.LiveData
import com.example.dependencyinjectionsquare.data.model.common.ArticleDataModel

interface DashboardViewModel {
    val categoryListLiveData : LiveData<List<String>>
    val toFavoriteLiveData : LiveData<Unit>
    val openCategoryLiveData : LiveData<String>


    fun openArticlesByCategory(category: String)

    fun toFavoriteScreen()
}