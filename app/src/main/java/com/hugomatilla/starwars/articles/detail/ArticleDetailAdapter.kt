package com.hugomatilla.starwars.articles.detail

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hugomatilla.starwars.R
import com.hugomatilla.starwars.base.ctx
import com.hugomatilla.starwars.domain.model.SectionDomain
import org.jetbrains.anko.AnkoLogger


/**
 * Created by hugomatilla on 28/02/16.
 */

class ArticleDetailAdapter(val sections: Collection<SectionDomain>) :
        RecyclerView.Adapter<ArticleDetailAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.ctx).inflate(R.layout.section, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindSection(sections.elementAt(position))
    }

    override fun getItemCount() = sections.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view), AnkoLogger {
        fun bindSection(section: SectionDomain) {
            with(section) {
                (itemView as SectionView).withSection(section)
//                if (section.image.isNullOrEmpty())
//                    itemView.sectionImageView.visibility = View.GONE
//                else {
//                    Glide.with(itemView.ctx).load(section.image).crossFade().into(itemView.sectionImageView)
//                    itemView.sectionImageView.visibility = View.VISIBLE
//                }
//                info("Adapter title:${section.title}, image:${section.image}, View: ${itemView.sectionImageView.visibility }")
//                itemView.sectionTitleView.text = title
//                itemView.sectionDescriptionView.text = text
            }
        }
    }

}