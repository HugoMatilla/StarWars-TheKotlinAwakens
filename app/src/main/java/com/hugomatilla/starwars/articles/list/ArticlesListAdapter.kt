package com.hugomatilla.starwars.articles.list

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.hugomatilla.starwars.R
import com.hugomatilla.starwars.base.ctx
import com.hugomatilla.starwars.domain.model.ArticleDomain
import kotlinx.android.synthetic.main.articles_list_item.view.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

/**
 * Created by hugomatilla on 28/02/16.
 */

class ArticlesListAdapter(var articlesList: Collection<ArticleDomain>, val itemClick: (ArticleDomain, View) -> Unit) : RecyclerView.Adapter<ArticlesListAdapter.ViewHolder>(), AnkoLogger {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.ctx).inflate(R.layout.articles_list_item, parent, false)
        return ViewHolder(view, itemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindArticle(articlesList.elementAt(position))
    }

    override fun getItemCount() = articlesList.size

    class ViewHolder(view: View, val itemClick: (ArticleDomain, View) -> Unit) : RecyclerView.ViewHolder(view), AnkoLogger {
        fun bindArticle(article: ArticleDomain) {
            with(article) {
                Glide.with(itemView.ctx).load(thumbnail).crossFade().into(itemView.articleListImageView)
                itemView.articleListNameView.text = title
                itemView.setOnClickListener {

                    itemClick(this, itemView.findViewById(R.id.articleListImageView))

                }
            }
        }
    }


}