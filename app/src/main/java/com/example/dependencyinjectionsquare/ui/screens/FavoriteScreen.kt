package com.example.dependencyinjectionsquare.ui.screens

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.dependencyinjectionsquare.R
import com.example.dependencyinjectionsquare.data.model.common.ArticleDataModel
import com.example.dependencyinjectionsquare.data.source.local.ArticlesDataBase
import com.example.dependencyinjectionsquare.databinding.ScreenArticleBinding
import com.example.dependencyinjectionsquare.databinding.ScreenFavoriteBinding
import com.example.dependencyinjectionsquare.presenter.ArticleViewModel
import com.example.dependencyinjectionsquare.presenter.FavoriteViewModel
import com.example.dependencyinjectionsquare.presenter.impl.ArticleViewModelImpl
import com.example.dependencyinjectionsquare.presenter.impl.FavoriteViewModelImpl
import com.example.dependencyinjectionsquare.ui.adapters.ArticlesAdapter
import com.example.dependencyinjectionsquare.ui.adapters.FavoriteAdapter
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FavoriteScreen : Fragment(R.layout.screen_favorite) {

    private val binding by viewBinding(ScreenFavoriteBinding::bind)
    private val viewModel : FavoriteViewModel by viewModels<FavoriteViewModelImpl>()
    private val navController by lazy(LazyThreadSafetyMode.NONE) { findNavController() }
    private val adapter by lazy { FavoriteAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.backPressedLiveData.observe(this , backPressed)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.include.favBtn.visibility = View.INVISIBLE

        binding.recyclerView.adapter = adapter
        viewModel.favoriteListLiveData.observe(this , loadFavoriteListObserver)



        binding.include.backBtn.setOnClickListener { viewModel.back() }
        binding.include.title.text = "Favorities"

        adapter.initClickListener {
            viewModel.deleteFromFavorite(it.title)
        }
    }


    private val loadFavoriteListObserver = Observer<List<ArticleDataModel>>{
        adapter.submitList(it)
    }

    private val backPressed = Observer<Unit> {
        navController.navigateUp()
    }






}