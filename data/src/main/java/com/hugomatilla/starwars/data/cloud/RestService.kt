package com.hugomatilla.starwars.data.cloud


/**
 * Created by hugomatilla on 27/02/16.
 */

import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.hugomatilla.starwars.data.cloud.exception.RepositoryError
import com.hugomatilla.starwars.data.cloud.model.ArticleContentCloud
import com.hugomatilla.starwars.data.cloud.model.ArticleListCloud
import com.hugomatilla.starwars.data.cloud.model.CloudMapper
import com.hugomatilla.starwars.domain.articles.GetArticleDetailResult
import com.hugomatilla.starwars.domain.articles.GetArticlesListResult
import java.net.MalformedURLException
import java.net.URL

class RestService(val cloudMapper: CloudMapper = CloudMapper(),
                  val gson: Gson = Gson()) {

    companion object Uris {
        val BASE_URL = "http://starwars.wikia.com/api/v1/"
        val TOP_ARTICLES_PART = "Articles/Top?expand=1&abstract=50&width=300&height=300"
        val TOP_ARTICLES_URL = BASE_URL + TOP_ARTICLES_PART
        val DETAIL_ARTICLE_PART = "Articles/AsSimpleJson/?id="
        val DETAIL_ARTICLE_URL = BASE_URL + DETAIL_ARTICLE_PART
    }

    fun fetchArticlesList(url: String = Uris.TOP_ARTICLES_URL): GetArticlesListResult {
        try {
            val jsonStr = URL(url).readText()
            val articleList = gson.fromJson(jsonStr, ArticleListCloud::class.java)
            val result = cloudMapper.articleListToDomain(articleList)
            return GetArticlesListResult(result)

        } catch(error: MalformedURLException) {
            return GetArticlesListResult(null, RepositoryError(error))

        } catch(error: JsonSyntaxException) {
            return GetArticlesListResult(null, RepositoryError(error))
        }
    }

    fun fetchArticleDetail(id: Int, url: String = Uris.DETAIL_ARTICLE_URL): GetArticleDetailResult {
        try {
            val jsonStr = URL(url + id).readText()
            val content = gson.fromJson(jsonStr, ArticleContentCloud::class.java)
            val sections = cloudMapper.articleContentToDomain(content)
            return GetArticleDetailResult(sections)

        } catch(error: MalformedURLException) {
            return GetArticleDetailResult(null, RepositoryError(error))

        } catch(error: JsonSyntaxException) {
            return GetArticleDetailResult(null, RepositoryError(error))
        }
    }
}