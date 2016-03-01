package com.hugomatilla.starwars.domain.articles

/**
 * Created by hugomatilla on 27/02/16.
 */

interface IArticlesListRepository {

    fun getArticleList(): GetArticlesResult
}
