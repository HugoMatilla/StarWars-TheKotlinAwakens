package com.hugomatilla.starwars.data.cloud

import com.hugomatilla.starwars.data.DataProvider
import com.hugomatilla.starwars.domain.ArticleDomain
import com.hugomatilla.starwars.domain.IArticleListRepository

/**
 * Created by hugomatilla on 27/02/16.
 */

object ArticleRepository : IArticleListRepository {

    var dataProvider: DataProvider = DataProvider()

    override fun getArticleList(): Collection<ArticleDomain>? {
        val dataStore = dataProvider.requestSource()
        return dataStore.getArticleList()
    }
}