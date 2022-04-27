package com.example.dependencyinjectionsquare.di

import com.example.dependencyinjectionsquare.domain.repository.ArticlesRepository
import com.example.dependencyinjectionsquare.domain.repository.impl.ArticlesRepositoriesImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)

interface RepositoryModule {


    @Binds
    @Singleton
    fun bindRepository(impl: ArticlesRepositoriesImpl) : ArticlesRepository
}