package com.hugomatilla.starwars.articles.detail

import android.content.Context
import android.util.AttributeSet
import com.bumptech.glide.Glide
import com.hugomatilla.starwars.R
import com.hugomatilla.starwars.base.BaseLinearLayout
import com.hugomatilla.starwars.base.ctx
import kotlinx.android.synthetic.main.article_detail_header.view.*

/**
 * Created by hugomatilla on 23/05/16.
 */

class DetailHeaderView : BaseLinearLayout {

    constructor(context: Context) : super(context) {
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
    }

    override fun getLayoutResource() = R.layout.article_detail_header

    fun withImage(imageUrl: String) {
        Glide.with(ctx).load(imageUrl).into(header_image)
    }
}