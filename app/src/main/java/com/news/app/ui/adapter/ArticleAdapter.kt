package com.news.app.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.news.app.R
import com.news.app.data.models.Article
import com.news.app.databinding.LayoutArticlesBinding

class ArticleAdapter(
    val onItemCLickListener: OnItemCLickListener
) : RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>() {

    private val listOfRepos: MutableList<Article> = mutableListOf()

    interface OnItemCLickListener {
        fun onItemClick(article: Article)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(
            DataBindingUtil.bind<LayoutArticlesBinding>(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.layout_articles, parent, false
                )
            ) as LayoutArticlesBinding
        )
    }

    override fun getItemCount(): Int {
        return listOfRepos.size
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.bind(listOfRepos[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(articles: List<Article>) {
        listOfRepos.clear()
        listOfRepos.addAll(articles)
        notifyDataSetChanged()
    }

    inner class ArticleViewHolder(private val viewBinding: LayoutArticlesBinding)
        : RecyclerView.ViewHolder(viewBinding.root) {

        fun bind(article: Article) {
            viewBinding.data = article
            viewBinding.listener = onItemCLickListener
        }

    }
}