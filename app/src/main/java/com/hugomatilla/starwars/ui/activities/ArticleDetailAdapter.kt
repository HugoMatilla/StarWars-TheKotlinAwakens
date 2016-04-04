package com.hugomatilla.starwars.ui.activities

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.hugomatilla.starwars.R
import com.hugomatilla.starwars.domain.model.SectionDomain
import kotlinx.android.synthetic.main.article_detail_item.view.*


/**
 * Created by hugomatilla on 28/02/16.
 */

class ArticleDetailAdapter(val sections: Collection<SectionDomain>) :
        RecyclerView.Adapter<ArticleDetailAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.ctx).inflate(R.layout.article_detail_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindSection(sections.elementAt(position))
    }

    override fun getItemCount() = sections.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bindSection(section: SectionDomain) {
            with(section) {
                Glide.with(itemView.ctx).load(section.image).crossFade().into(itemView.sectionImageView)
                itemView.sectionTitleView.text = title
                itemView.sectionDescriptionView.text = text
            }
        }
    }

}