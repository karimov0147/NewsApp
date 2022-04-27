package com.example.dependencyinjectionsquare.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dependencyinjectionsquare.R
import com.example.dependencyinjectionsquare.data.model.common.ArticleDataModel
import com.example.dependencyinjectionsquare.databinding.ArticleItemBinding
import com.example.dependencyinjectionsquare.databinding.ArticleItemTwoBinding

class FavoriteAdapter : ListAdapter<ArticleDataModel ,FavoriteAdapter.ViewHolder>(DiffUtilCallBack) {

    private var listener : ((ArticleDataModel) -> Unit)? = null

    object DiffUtilCallBack : DiffUtil.ItemCallback<ArticleDataModel>(){
        override fun areItemsTheSame(
            oldItem: ArticleDataModel,
            newItem: ArticleDataModel
        ): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(
            oldItem: ArticleDataModel,
            newItem: ArticleDataModel
        ): Boolean {
            return oldItem.title == newItem.title && oldItem.isFav == newItem.isFav
        }

    }

    fun initClickListener(l : ((ArticleDataModel) -> Unit)  ){
        listener = l
    }

    inner class ViewHolder(private val view : ArticleItemTwoBinding) : RecyclerView.ViewHolder(view.root){
        init {
            view.root.setOnClickListener { listener?.invoke(getItem(bindingAdapterPosition)) }
        }
        fun bind(){
            view.apply {
                val item = getItem(bindingAdapterPosition)
                textAuthor.text = getItem(bindingAdapterPosition).title
                textDescription.text = getItem(bindingAdapterPosition).description
                category.text = item.category
                btnFav.setImageResource(if (item.isFav) R.drawable.ic_baseline_star_24 else R.drawable.ic_baseline_star_border_24)
                Glide.with(itemView)
                    .load(getItem(bindingAdapterPosition).imageUrl)
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(image)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        ArticleItemTwoBinding.inflate(LayoutInflater.from(parent.context) , parent , false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()
    }
}