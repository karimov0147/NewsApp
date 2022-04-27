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


class ArticlesAdapter : ListAdapter<ArticleDataModel ,ArticlesAdapter.ViewHolder>(DiffUtilCallBack) {

    private var listener : ((String , Boolean) -> Unit)? = null

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
            return oldItem.title == newItem.title &&
                    oldItem.author == newItem.author &&
                    oldItem.isFav == newItem.isFav
        }

    }

    inner class ViewHolder(private val view : ArticleItemBinding) : RecyclerView.ViewHolder(view.root){
        init {
            view.btnFav.setOnClickListener {
                val item = getItem(bindingAdapterPosition)
                listener?.invoke(item.title , !item.isFav)
            }
        }
        fun bind(){
            view.apply {
                val item = getItem(bindingAdapterPosition)
                textAuthor.text = getItem(bindingAdapterPosition).title
                textDescription.text = getItem(bindingAdapterPosition).description
                btnFav.setImageResource(if (item.isFav) R.drawable.ic_baseline_star_24 else R.drawable.ic_baseline_star_border_24)
                Glide.with(itemView)
                    .load(getItem(bindingAdapterPosition).imageUrl)
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(image)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        ArticleItemBinding.inflate(LayoutInflater.from(parent.context) , parent , false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()
    }

    fun initClickListener(l : ((String , Boolean) -> Unit)){
        listener = l
    }


}