package com.hugomatilla.starwars.data.cloud


/**
 * Created by hugomatilla on 27/02/16.
 */

import android.util.Log
import com.google.gson.Gson
import com.hugomatilla.starwars.data.cloud.model.CloudMapper
import com.hugomatilla.starwars.domain.ArticleDomain
import java.net.URL

class Request(val cloudMapper: CloudMapper = CloudMapper(),
              val gson: Gson = Gson()) {

    fun execute(): Collection<ArticleDomain> {
        val jsonStr = URL(RestUrls.topArticles).readText()
        Log.d(javaClass.canonicalName, "REQUEST: " + jsonStr.toString())
        val articleList = gson.fromJson(jsonStr, ArticleListCloud::class.java)
        val articles = cloudMapper.articleListToDomain(articleList)
        return articles
    }
}