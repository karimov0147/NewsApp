package com.example.dependencyinjectionsquare.di

import com.example.dependencyinjectionsquare.data.source.local.dao.ArticleDao
import com.example.dependencyinjectionsquare.data.source.remote.ArticleApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    @[Provides  Singleton]
    fun providerApi(retrofit: Retrofit) : ArticleApi = retrofit.create(ArticleApi::class.java)


}