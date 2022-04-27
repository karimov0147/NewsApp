package com.example.dependencyinjectionsquare.di

import android.content.Context
import com.example.dependencyinjectionsquare.data.source.local.ArticlesDataBase
import com.example.dependencyinjectionsquare.data.source.local.dao.ArticleDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {


    @Provides
    fun providesArticleDataBase (@ApplicationContext context: Context) : ArticlesDataBase = ArticlesDataBase.init(context)

    @Provides
    fun provideArticleDao(articlesDataBase: ArticlesDataBase) : ArticleDao = articlesDataBase.getArticleDao()

}