package com.example.dependencyinjectionsquare.domain.repository.impl

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dependencyinjectionsquare.data.model.common.ArticleDataModel
import com.example.dependencyinjectionsquare.data.model.response.BaseResponse
import com.example.dependencyinjectionsquare.data.source.local.Categories
import com.example.dependencyinjectionsquare.data.source.local.dao.ArticleDao
import com.example.dependencyinjectionsquare.data.source.local.toEntity
import com.example.dependencyinjectionsquare.data.source.remote.ArticleApi
import com.example.dependencyinjectionsquare.domain.repository.ArticlesRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class ArticlesRepositoriesImpl @Inject constructor(
    private val articleApi: ArticleApi,
    private val articleDao: ArticleDao
) : ArticlesRepository {

    var loadingLiveData = MutableLiveData(false)


    override fun loadArticlesByCategory(category: String): LiveData<List<ArticleDataModel>> {
        loadingLiveData.value = true
        articleApi.getNewsByCategory(category).enqueue( object : Callback<BaseResponse>{
            override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                if (response.isSuccessful && response.body() != null){
                    loadingLiveData.value = false
                    val result = response.body()?.data ?: arrayListOf()
                    articleDao.insertAll(result.map { it.toEntity(category) })
                }
            }

            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                    loadingLiveData.value = false
            }

        })

        return articleDao.getAllArticles(category)
    }

    override fun getCategoryList(): List<String> {
       return Categories.getAllCategory()
    }

    override fun loadingState(): LiveData<Boolean> = loadingLiveData

    override fun loadArticlesByCategoryFromCache(category: String): LiveData<List<ArticleDataModel>> = articleDao.getAllArticles(category)


    override fun getFavoriteArticles(): LiveData<List<ArticleDataModel>> {
        Log.d("TTT" , articleDao.getFavoriteArticles(true).toString())
        return articleDao.getFavoriteArticles(true)

    }

    override fun changeStateArticle(title: String, state: Boolean) {
        Log.d("TTT" ,"state is changed")
       articleDao.updateArticle(state , title)
    }


}