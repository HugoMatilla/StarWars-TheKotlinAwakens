package com.hugomatilla.starwars.data.cloud

import com.hugomatilla.starwars.data.IDataSource
import com.hugomatilla.starwars.domain.articles.GetArticleDetailResult
import com.hugomatilla.starwars.domain.articles.GetArticlesListResult

/**
 * Created by hugomatilla on 27/02/16.
 */

class Cloud : IDataSource {
    override fun getArticleList(): GetArticlesListResult {
        return RestService().fetchArticlesList()
    }

    override fun getArticleDetail(id: Int): GetArticleDetailResult {
        return RestService().fetchArticleDetail(id)
    }
}