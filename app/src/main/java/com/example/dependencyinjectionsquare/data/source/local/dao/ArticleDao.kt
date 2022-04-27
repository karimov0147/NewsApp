package com.example.dependencyinjectionsquare.data.source.local.dao

import android.icu.text.CaseMap
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.dependencyinjectionsquare.data.model.common.ArticleDataModel
import com.example.dependencyinjectionsquare.data.model.response.BaseResponse
import com.example.dependencyinjectionsquare.data.source.local.entity.ArticleEntity

@Dao
interface ArticleDao : BaseDao<ArticleEntity> {

    @Query("select * from ArticleEntity where category = :category")
    fun getAllArticles(category: String) : LiveData<List<ArticleDataModel>>

    @Query("update ArticleEntity set  isFav= :favState where title= :title")
    fun updateArticle(favState : Boolean , title: String)

    @Query("select * from ArticleEntity where isFav = :state ")
    fun getFavoriteArticles(state : Boolean) : LiveData<List<ArticleDataModel>>






}