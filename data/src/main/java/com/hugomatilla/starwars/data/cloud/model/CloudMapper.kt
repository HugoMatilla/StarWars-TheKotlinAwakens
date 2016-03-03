package com.hugomatilla.starwars.data.cloud.model

import com.hugomatilla.starwars.domain.model.ArticleDetailDomain
import com.hugomatilla.starwars.domain.model.SectionContent

/**
 * Created by hugomatilla on 27/02/16.
 */

class CloudMapper {
    companion object {
        val PLACE_HOLDER = "https://s-media-cache-ak0.pinimg.com/236x/8e/8c/06/8e8c0619aa7db68b0fd9fbb53f2be96b.jpg"
    }

    fun articleListToDomain(articleList: ArticleListCloud): List<ArticleDetailDomain> {
        return articleList.items.map { articleDetailToDomain(it) }
    }

    fun articleDetailToDomain(article: ArticleDetailCloud, content: ArticleContentCloud? = null): ArticleDetailDomain {
        return with(article) {
            ArticleDetailDomain(
                    id,
                    title,
                    abstract,
                    thumbnail ?: PLACE_HOLDER,
                    getFullImageUrl(thumbnail),
                    original_dimensions?.width,
                    original_dimensions?.height,
                    url,
                    type,
                    articleContentToDomain(content)
            )
        }
    }

    fun articleContentToDomain(content: ArticleContentCloud?): Collection<SectionContent>? {
        return content?.sections?.map {
            SectionContent(
                    it.title,
                    it.level,
                    it.content.joinToString("\n"),
                    it.images.firstOrNull()?.src,
                    it.images.firstOrNull()?.caption
            )
        }
    }


    private fun getFullImageUrl(thumbnail: String?) = thumbnail?.replace("width/100", "width/500")
}