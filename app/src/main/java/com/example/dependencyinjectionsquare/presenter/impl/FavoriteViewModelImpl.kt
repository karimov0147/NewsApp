package com.example.dependencyinjectionsquare.presenter.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dependencyinjectionsquare.data.model.common.ArticleDataModel
import com.example.dependencyinjectionsquare.domain.repository.ArticlesRepository
import com.example.dependencyinjectionsquare.presenter.FavoriteViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModelImpl  @Inject constructor (
    val  repository : ArticlesRepository
) : ViewModel() , FavoriteViewModel {

    override var backPressedLiveData: MutableLiveData<Unit> = MutableLiveData()
    override var favoriteListLiveData : LiveData<List<ArticleDataModel>> =  MutableLiveData()


    init  {
        favoriteListLiveData = repository.getFavoriteArticles()
    }

    override fun back() {
       backPressedLiveData.value = Unit
    }

    override fun deleteFromFavorite(title : String) {
        repository.changeStateArticle(title , false)
    }
}