package com.hugomatilla.starwars.data.cloud.model

import com.hugomatilla.starwars.domain.model.ArticleDomain
import com.hugomatilla.starwars.domain.model.SectionDomain

fun String?.toFullSize(): String? {
    if (this != null) {
        val fullSize = this.split("/revision")
        return fullSize[0]
    } else
        return null
}

/**
 * Created by hugomatilla on 27/02/16.
 */

class CloudMapper {
    companion object {
        val PLACE_HOLDER = "http://assets2.ignimgs.com/2015/08/06/darth-vader-crossed-arms-1280jpg-88461e1280wjpg-67c0c2_1280w.jpg"
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
                    it.images.firstOrNull()?.src.toFullSize(),
                    it.images.firstOrNull()?.caption
            )
        }
    }


}