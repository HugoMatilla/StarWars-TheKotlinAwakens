package com.hugomatilla.starwars.domain2.articles

import com.hugomatilla.starwars.domain2.model.ArticleDomain

/**
 * Created by hugomatilla on 27/02/16.
 */

interface IArticlesListRepository {

    fun getArticleList(): Collection<ArticleDomain>?
    fun deleteDatabase()
}

interface IArticleDetailRepository {

    fun getArticleDetail(id: Int): ArticleDomain?
}