package com.hugomatilla.starwars.domain.articles

import com.hugomatilla.starwars.domain.model.ArticleDomain

/**
 * Created by hugomatilla on 27/02/16.
 */

interface IArticlesListRepository {

    fun getArticleList(): Collection<ArticleDomain>?
    fun deleteAll()
}

interface IArticleDetailRepository {

    fun getArticleDetail(id: Int): ArticleDomain?
}