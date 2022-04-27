package com.example.dependencyinjectionsquare.presenter

import androidx.lifecycle.LiveData
import androidx.room.Delete
import com.example.dependencyinjectionsquare.data.model.common.ArticleDataModel

interface FavoriteViewModel {

    val favoriteListLiveData : LiveData<List<ArticleDataModel>>
    val backPressedLiveData : LiveData<Unit>


    fun back()

    fun deleteFromFavorite( title  :String)
}