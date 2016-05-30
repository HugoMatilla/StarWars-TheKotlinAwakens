package com.hugomatilla.starwars.articles.detail

import android.content.Context
import android.util.AttributeSet
import com.bumptech.glide.Glide
import com.hugomatilla.starwars.R
import com.hugomatilla.starwars.base.BaseLinearLayout
import com.hugomatilla.starwars.base.ctx
import com.hugomatilla.starwars.domain.model.SectionDomain
import kotlinx.android.synthetic.main.section.view.*

/**
 * Created by hugomatilla on 23/05/16.
 */

class SectionView : BaseLinearLayout {

    constructor(context: Context) : super(context) {
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
    }

    override fun getLayoutResource() = R.layout.section

    fun withSection(section: SectionDomain) {
        Glide.with(ctx).load(section.image).into(image)
        image.contentDescription = section.caption
        content.text = section.text
        title.text = section.title
        caption.text = section.caption
    }
}