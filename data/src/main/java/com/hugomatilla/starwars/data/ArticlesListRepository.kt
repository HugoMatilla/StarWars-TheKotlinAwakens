package com.hugomatilla.starwars.data

import com.hugomatilla.starwars.domain.articles.GetArticlesListResult
import com.hugomatilla.starwars.domain.articles.IArticlesListRepository

/**
 * Created by hugomatilla on 27/02/16.
 */

object ArticlesListRepository : IArticlesListRepository {

    val dataProvider: DataProvider = DataProvider()

    override fun getArticleList(): GetArticlesListResult {
        val dataStore = dataProvider.requestSource()
        val result = dataStore.getArticleList();
        return result
    }
}