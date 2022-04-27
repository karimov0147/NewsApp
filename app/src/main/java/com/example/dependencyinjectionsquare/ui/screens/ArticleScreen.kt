package com.example.dependencyinjectionsquare.ui.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.navigateUp
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.dependencyinjectionsquare.R
import com.example.dependencyinjectionsquare.data.model.common.ArticleDataModel
import com.example.dependencyinjectionsquare.databinding.ScreenArticleBinding
import com.example.dependencyinjectionsquare.presenter.ArticleViewModel
import com.example.dependencyinjectionsquare.presenter.impl.ArticleViewModelImpl
import com.example.dependencyinjectionsquare.ui.adapters.ArticlesAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArticleScreen : Fragment(R.layout.screen_article) {

    private val binding by viewBinding(ScreenArticleBinding::bind)
    private val viewModel : ArticleViewModel by viewModels<ArticleViewModelImpl>()
    private val navController by lazy(LazyThreadSafetyMode.NONE) { findNavController() }
    private val adapter by lazy { ArticlesAdapter() }
    private val args : ArticleScreenArgs by navArgs()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.backLiveData.observe(this , backPressedObserver )


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.include.title.text = args.category
        viewModel.loadArticlesByCategory(args.category)
        adapter.initClickListener { title, state ->
           // Toast.makeText(requireContext(), state.toString(), Toast.LENGTH_SHORT).show()
            viewModel.updateArticle(title , state)
        }
        viewModel.loadingLiveData.observe(viewLifecycleOwner , loadingObserver)
        viewModel.articlesLiveData.observe(this , articleListObserver)
        binding.recyclerView.adapter = adapter
        binding.include.favBtn.visibility = View.INVISIBLE

        binding.include.backBtn.setOnClickListener{
            viewModel.backPressed()
        }


       // binding.recyclerView.itemAnimator = null
    }


    private val loadingObserver = Observer<Boolean>{
        binding.progress.visibility = if (it) View.VISIBLE else View.GONE }

    private val articleListObserver = Observer<List<ArticleDataModel>> {
        adapter.submitList(it)
    }
    private val backPressedObserver = Observer<Unit> {
        navController.navigateUp()
    }


}