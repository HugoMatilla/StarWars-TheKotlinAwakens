package com.hugomatilla.starwars.domain

/**
 * Created by hugomatilla on 27/02/16.
 */

interface IArticleListRepository {

    fun getArticleList(): Collection<ArticleDomain>?
}
