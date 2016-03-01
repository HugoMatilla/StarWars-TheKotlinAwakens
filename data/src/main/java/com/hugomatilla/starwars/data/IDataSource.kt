package com.hugomatilla.starwars.data

import com.hugomatilla.starwars.domain.articles.GetArticlesResult

/**
 * Created by hugomatilla on 28/02/16.
 */

interface IDataSource {
    fun getArticleList(): GetArticlesResult
}