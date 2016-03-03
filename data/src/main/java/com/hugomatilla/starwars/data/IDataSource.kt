package com.hugomatilla.starwars.data

import com.hugomatilla.starwars.domain.articles.GetArticleDetailResult
import com.hugomatilla.starwars.domain.articles.GetArticlesListResult

/**
 * Created by hugomatilla on 28/02/16.
 */

interface IDataSource {
    fun getArticleList(): GetArticlesListResult
    fun getArticleDetail(id: Int): GetArticleDetailResult
}