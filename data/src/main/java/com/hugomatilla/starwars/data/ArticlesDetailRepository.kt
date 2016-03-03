package com.hugomatilla.starwars.data

import com.hugomatilla.starwars.domain.articles.GetArticleDetailResult
import com.hugomatilla.starwars.domain.articles.IArticleDetailRepository

/**
 * Created by hugomatilla on 27/02/16.
 */

object ArticlesDetailRepository : IArticleDetailRepository {

    val dataProvider: DataProvider = DataProvider()

    override fun getArticleDetail(id: Int): GetArticleDetailResult {
        val dataStore = dataProvider.requestSource()
        val result = dataStore.getArticleDetail(id);
        return result
    }
}