package com.example.dependencyinjectionsquare.ui.screens

import android.os.Bundle
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.dependencyinjectionsquare.R
import com.example.dependencyinjectionsquare.databinding.ScreenDashboardBinding
import com.example.dependencyinjectionsquare.presenter.DashboardViewModel
import com.example.dependencyinjectionsquare.presenter.impl.DashboardViewModelImpl
import com.example.dependencyinjectionsquare.ui.adapters.DashboardAdapter
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
@AndroidEntryPoint
class DashboardScreen : Fragment(R.layout.screen_dashboard) {

    private val binding by viewBinding(ScreenDashboardBinding::bind)
    private val viewModel: DashboardViewModel by viewModels<DashboardViewModelImpl>()
    private val navController by lazy(LazyThreadSafetyMode.NONE) { findNavController() }
    private val adapter by lazy { DashboardAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.categoryListLiveData.observe(this , categoryObserver)
        viewModel.openCategoryLiveData.observe(this , openArticleScreenObserver )
        viewModel.toFavoriteLiveData.observe(this , toFavoriteScreenObserver)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.recyclerView.adapter = adapter
        adapter.initOnclickListener {
            viewModel.openArticlesByCategory(it)
        }
        binding.include.backBtn.visibility = View.INVISIBLE
        binding.include.favBtn.setOnClickListener {
            viewModel.toFavoriteScreen()
        }
        binding.include.title.text = "Categories"
    }

    private val categoryObserver = Observer<List<String>> { adapter.submitList(it) }
    private val openArticleScreenObserver = Observer<String>{
        navController.navigate(DashboardScreenDirections.actionDashboardScreenToArticleScreen(it))
    }
    private val toFavoriteScreenObserver = Observer<Unit> {
        navController.navigate(R.id.action_dashboardScreen_to_favoriteScreen2)
    }
}