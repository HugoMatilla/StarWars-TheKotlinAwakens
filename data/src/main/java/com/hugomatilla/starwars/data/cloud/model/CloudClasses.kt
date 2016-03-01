package com.hugomatilla.starwars.data.cloud.model

/**
 * Created by hugomatilla on 27/02/16.
 */

data class ArticleListCloud(val items: List<ArticleCloud>)

data class ArticleCloud(
        val id: Int,
        val title: String,
        val abstract: String,
        val thumbnail: String?,
        val original_dimensions: Dimensions?,
        val url: String,
        val type: String
)

data class Dimensions(
        val width: Int,
        val height: Int
)
