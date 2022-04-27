package com.example.dependencyinjectionsquare.presenter.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dependencyinjectionsquare.data.model.common.ArticleDataModel
import com.example.dependencyinjectionsquare.domain.repository.ArticlesRepository
import com.example.dependencyinjectionsquare.presenter.DashboardViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DashboardViewModelImpl @Inject constructor(
     repository : ArticlesRepository
) : DashboardViewModel , ViewModel() {

    override val categoryListLiveData: MutableLiveData<List<String>> = MutableLiveData(repository.getCategoryList())
    override val toFavoriteLiveData: MutableLiveData<Unit> = MutableLiveData()
    override val openCategoryLiveData: MutableLiveData<String> = MutableLiveData()


    override fun openArticlesByCategory(category: String) {
        openCategoryLiveData.value = category
    }

    override fun toFavoriteScreen() {
        toFavoriteLiveData.value = Unit
    }
}