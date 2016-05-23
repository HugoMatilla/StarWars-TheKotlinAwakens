package com.hugomatilla.starwars.data.cloud.model

import com.hugomatilla.starwars.domain.model.ArticleDomain
import com.hugomatilla.starwars.domain.model.SectionDomain

/**
 * Created by hugomatilla on 27/02/16.
 */

class CloudMapper {
    companion object {
        val PLACE_HOLDER = "https://s-media-cache-ak0.pinimg.com/236x/8e/8c/06/8e8c0619aa7db68b0fd9fbb53f2be96b.jpg"
    }

    fun articleListToDomain(articleList: ArticlesListCloud): List<ArticleDomain> {
        return articleList.items.map { articleDetailToDomain(it) }
    }

    fun articleDetailToDomain(article: ArticleAbstractCloud, sections: Collection<SectionDomain>? = null): ArticleDomain {
        return with(article) {
            ArticleDomain(
                    id,
                    title,
                    abstract,
                    thumbnail ?: PLACE_HOLDER,
                    original_dimensions?.width,
                    original_dimensions?.height,
                    url,
                    type,
                    sections
            )
        }
    }

    fun articleSectionsContentToDomain(sectionsContent: SectionsCloud?): Collection<SectionDomain>? {
        return sectionsContent?.sections?.map {
            SectionDomain(
                    it.title,
                    it.level,
                    it.content.map { it.text }.joinToString("\n"),
                    it.images.firstOrNull()?.src,
                    it.images.firstOrNull()?.caption
            )
        }
    }


}