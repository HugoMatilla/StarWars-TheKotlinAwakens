package com.hugomatilla.starwars.data.cloud.model

import com.hugomatilla.starwars.data.cloud.ArticleCloud
import com.hugomatilla.starwars.data.cloud.ArticleListCloud
import com.hugomatilla.starwars.domain.ArticleDomain

/**
 * Created by hugomatilla on 27/02/16.
 */

class CloudMapper {
    companion object {
        val PLACE_HOLDER = "https://s-media-cache-ak0.pinimg.com/236x/8e/8c/06/8e8c0619aa7db68b0fd9fbb53f2be96b.jpg"
    }

    fun articleListToDomain(articleList: ArticleListCloud): List<ArticleDomain> {
        return articleList.items.map { articleToDomain(it) }
    }

    private fun articleToDomain(article: ArticleCloud): ArticleDomain {
        return with(article) {
            val thumbnailAux = thumbnail ?: PLACE_HOLDER
            val thumbnailFull = thumbnailAux.replace("width/100", "width/500")
            ArticleDomain(id, title, abstract, thumbnailAux, thumbnailFull, original_dimensions?.width, original_dimensions?.height, url, type)
        }
    }
}