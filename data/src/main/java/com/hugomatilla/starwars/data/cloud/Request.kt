package com.hugomatilla.starwars.data.cloud


/**
 * Created by hugomatilla on 27/02/16.
 */

import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.hugomatilla.starwars.data.cloud.exception.RepositoryError
import com.hugomatilla.starwars.data.cloud.model.ArticleListCloud
import com.hugomatilla.starwars.data.cloud.model.CloudMapper
import com.hugomatilla.starwars.domain.articles.GetArticlesResult
import java.net.MalformedURLException
import java.net.URL

class Request(val cloudMapper: CloudMapper = CloudMapper(),
              val gson: Gson = Gson()) {

    object Uris {
        private val baseUrl = "htp://starwars.wikia.com/api/v1/"
        val topArticles = baseUrl + "Articles/Top?expand=1&abstract=50&width=300&height=300"
        val articleDetail = baseUrl + "Articles/AsSimpleJson/?id=%s"
    }

    fun fetchArticlesList(): GetArticlesResult {
        try {
            val jsonStr = URL(Uris.topArticles).readText()
            val articleList = gson.fromJson(jsonStr, ArticleListCloud::class.java)
            val result = cloudMapper.articleListToDomain(articleList)
            return GetArticlesResult(result)
        } catch(error: MalformedURLException) {
            return GetArticlesResult(null, RepositoryError(error))
        } catch(error: JsonSyntaxException) {
            return GetArticlesResult(null, RepositoryError(error))
        }
    }
}