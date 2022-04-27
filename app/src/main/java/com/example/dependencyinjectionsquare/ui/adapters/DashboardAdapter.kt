package com.example.dependencyinjectionsquare.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.dependencyinjectionsquare.R
import com.example.dependencyinjectionsquare.databinding.DashboardItemBinding

class DashboardAdapter : ListAdapter<String , DashboardAdapter.ViewHolder>(DashboardCallback) {

    lateinit var onClickListener : ((String) -> Unit)

    object DashboardCallback : DiffUtil.ItemCallback<String>(){
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean = oldItem ==newItem

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean = oldItem ==newItem

    }

    inner class ViewHolder(private val view : DashboardItemBinding) : RecyclerView.ViewHolder(view.root){
        init {
            view.root.setOnClickListener { onClickListener.invoke(getItem(bindingAdapterPosition)) }
        }
        fun bind(){
            view.title.text = getItem(bindingAdapterPosition)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        DashboardItemBinding.inflate(LayoutInflater.from(parent.context) , parent , false))


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()
    }

    fun initOnclickListener(l : ((String) -> Unit)){
        onClickListener = l
    }
}

