package com.hugomatilla.starwars.data.db.model

import com.hugomatilla.starwars.domain.model.ArticleDomain
import com.hugomatilla.starwars.domain.model.SectionDomain

/**
 * Created by hugomatilla on 20/03/16.
 */

class DbMapper {


    fun articlesFromDbToDomain(articles: Collection<ArticleDb>): Collection<ArticleDomain>? {
        return articles.map { articleFromDbToDomain(it, null) }
    }

    fun articleFromDbToDomain(article: ArticleDb, sections: Collection<SectionDb>?) = with(article) {
        ArticleDomain(_id.toInt(), title, abstract, thumbnail, thumbnail, width, height, url, type, sectionsFromDbToDomain(sections))
    }

    fun sectionsFromDbToDomain(sections: Collection<SectionDb>?): Collection<SectionDomain>? {
        return sections?.map { sectionDbToDomain(it) }
    }

    private fun sectionDbToDomain(section: SectionDb): SectionDomain {
        return with(section) { SectionDomain(title, level, content, image, caption) }
    }

    fun articlesFromDomainToDb(articles: Collection<ArticleDomain>): Collection<ArticleDb> {
        return articles.map { articleFromDomainToDb(it) }
    }

    fun articleFromDomainToDb(article: ArticleDomain, sections: Collection<SectionDb>? = null) = with(article)
    {
        ArticleDb(id.toLong(),
                title.orEmpty(),
                abstract.orEmpty(),
                thumbnail.orEmpty(),
                originalWidth ?: 0,
                originalHeight ?: 0,
                url.orEmpty(),
                type.orEmpty(),
                sections
        )
    }

    fun sectionsFromDomainToDb(parentArticleId: Int, sections: Collection<SectionDomain>): Collection<SectionDb> {
        return sections.map { sectionFromDomainToDb(parentArticleId, it) }
    }

    private fun sectionFromDomainToDb(parentArticleId: Int, section: SectionDomain) = with(section) {
        SectionDb(
                title.orEmpty(),
                level ?: 0,
                text.orEmpty(),
                image.orEmpty(),
                caption.orEmpty(),
                parentArticleId
        )
    }
}
