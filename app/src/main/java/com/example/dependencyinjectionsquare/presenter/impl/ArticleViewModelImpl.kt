package com.example.dependencyinjectionsquare.presenter.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dependencyinjectionsquare.data.model.common.ArticleDataModel
import com.example.dependencyinjectionsquare.domain.repository.ArticlesRepository
import com.example.dependencyinjectionsquare.presenter.ArticleViewModel
import com.example.dependencyinjectionsquare.utills.AppConnectionUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Retrofit
import javax.inject.Inject

@HiltViewModel
class ArticleViewModelImpl @Inject constructor(var retrofit: Retrofit , val repository: ArticlesRepository , private val connectionUtil: AppConnectionUtil) : ViewModel() , ArticleViewModel{
    override val loadingLiveData: LiveData<Boolean> = repository.loadingState()
    override val backLiveData: MutableLiveData<Unit> = MutableLiveData()
    override var articlesLiveData: LiveData<List<ArticleDataModel>> = MutableLiveData()

    override fun backPressed() {
        backLiveData.value = Unit
    }

    override fun loadArticlesByCategory(category: String) {
        articlesLiveData = if (connectionUtil.isConnected()) repository.loadArticlesByCategory(category) else repository.loadArticlesByCategoryFromCache(category)
    }

    override fun updateArticle(title: String, state: Boolean) {
        repository.changeStateArticle(title ,state)
    }


}