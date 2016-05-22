package com.hugomatilla.starwars.data.cloud

import com.hugomatilla.starwars.data.IReadableDataSource
import com.hugomatilla.starwars.data.db.Db
import com.hugomatilla.starwars.domain2.model.ArticleDomain
import com.hugomatilla.starwars.domain2.model.SectionDomain

/**
 * Created by hugomatilla on 27/02/16.
 */

class Cloud : IReadableDataSource {


    override fun getArticleList(): Collection<ArticleDomain>? {
        return RestService().fetchArticlesList()
    }

    override fun getArticleDetailById(id: Int): ArticleDomain? {
        val article = Db().getArticleDetailById(id)
        article?.sections = getArticleSections(id)
        return article
    }

    override fun getArticleSections(id: Int): Collection<SectionDomain>? {
        return RestService().fetchArticleSections(id)
    }
}