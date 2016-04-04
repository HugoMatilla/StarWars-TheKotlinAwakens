package com.hugomatilla.starwars.data.cloud


/**
 * Created by hugomatilla on 27/02/16.
 */

import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.hugomatilla.starwars.data.cloud.model.ArticlesListCloud
import com.hugomatilla.starwars.data.cloud.model.CloudMapper
import com.hugomatilla.starwars.data.cloud.model.SectionsCloud
import com.hugomatilla.starwars.domain.model.ArticleDomain
import com.hugomatilla.starwars.domain.model.SectionDomain
import java.net.MalformedURLException
import java.net.URL

class RestService(val cloudMapper: CloudMapper = CloudMapper(),
                  val gson: Gson = Gson()) {

    companion object Uris {
        val BASE_URL = "http://starwars.wikia.com/api/v1/"
        val TOP_ARTICLES_PART = "Articles/Top?expand=1&abstract=50&width=300&height=300"
        val TOP_ARTICLES_URL = BASE_URL + TOP_ARTICLES_PART
        val DETAIL_ARTICLE_PART = "Articles/Details/?ids="
        val SECTIONS_ARTICLE_PART = "Articles/AsSimpleJson/?id="
        val DETAIL_ARTICLE_URL = BASE_URL + DETAIL_ARTICLE_PART
        val SECTIONS_ARTICLE_URL = BASE_URL + SECTIONS_ARTICLE_PART
    }

    fun fetchArticlesList(url: String = Uris.TOP_ARTICLES_URL): Collection<ArticleDomain> {
        try {
            val jsonStr = URL(url).readText()
            val articleList = gson.fromJson(jsonStr, ArticlesListCloud::class.java)
            return cloudMapper.articleListToDomain(articleList)

        } catch(error: MalformedURLException) {
            throw error

        } catch(error: JsonSyntaxException) {
            throw error
        }
    }

    fun fetchArticleDetail(id: Int, url: String = Uris.DETAIL_ARTICLE_URL): ArticleDomain? {
        try {
            val jsonStr = URL(url + id).readText()
            val content = gson.fromJson(jsonStr, ArticlesListCloud::class.java)
            val a = "a"
            return cloudMapper.articleDetailToDomain(content.items.elementAt(0), fetchArticleSections(id))

        } catch(error: MalformedURLException) {
            throw error

        } catch(error: JsonSyntaxException) {
            throw error
        }
    }

    fun fetchArticleSections(id: Int, url: String = Uris.SECTIONS_ARTICLE_URL): Collection<SectionDomain>? {
        try {
            val jsonStr = URL(url + id).readText()
            val content = gson.fromJson(jsonStr, SectionsCloud::class.java)
            return cloudMapper.articleSectionsContentToDomain(content)

        } catch(error: MalformedURLException) {
            throw error

        } catch(error: JsonSyntaxException) {
            throw error
        }
    }
}