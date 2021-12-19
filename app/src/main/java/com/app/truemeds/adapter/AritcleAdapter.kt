package com.app.truemeds.adapter

import android.content.Context
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.truemeds.R
import com.app.truemeds.databinding.ItemArticleBinding
import com.app.truemeds.model.TrueMedsModel
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import java.util.concurrent.TimeUnit


class AritcleAdapter(private var articles: TrueMedsModel, private val context: Context) : RecyclerView.Adapter<AritcleAdapter.ArticleViewHolder>() {

    private lateinit var binding: ItemArticleBinding


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        binding = ItemArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArticleViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        binding.articleName.text = articles.result.article[position].name
        binding.articleAuthor.text = articles.result.article[position].author
        binding.articleCategory.text = articles.result.article[position].categoryName

        Glide.with(context)
            .load(articles.result.article[position].image)
            .apply(RequestOptions().placeholder(R.drawable.placeholder).error(R.drawable.placeholder))
            .into(binding.articleImage)
    }

    override fun getItemCount(): Int = articles.result.article.size


    class ArticleViewHolder(view: View) : RecyclerView.ViewHolder(view)

    fun setData(articles: TrueMedsModel) {
        this.articles = articles
        notifyDataSetChanged()
    }



}
