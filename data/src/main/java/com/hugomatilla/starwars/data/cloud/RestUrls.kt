package com.hugomatilla.starwars.data.cloud

/**
 * Created by hugomatilla on 27/02/16.
 */

object RestUrls {
    private val baseUrl = "http://starwars.wikia.com/api/v1/"
    val topArticles = baseUrl + "Articles/Top?expand=1&abstract=50&width=300&height=300"
    val articleDetail = baseUrl + "Articles/AsSimpleJson/?id=%s"
}