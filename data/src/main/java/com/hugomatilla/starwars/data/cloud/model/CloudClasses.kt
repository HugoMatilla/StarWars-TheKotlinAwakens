package com.hugomatilla.starwars.data.cloud.model

/**
 * Created by hugomatilla on 27/02/16.
 */

data class ArticlesListCloud(val items: List<ArticleAbstractCloud>)

data class ArticleAbstractCloud(
        val id: Int,
        val title: String,
        val abstract: String,
        val thumbnail: String?,
        val original_dimensions: Dimensions?,
        val url: String?,
        val type: String?
)

data class Dimensions(
        val width: Int,
        val height: Int
)

data class SectionsCloud(
        val sections: Collection<Section>?
)

data class Section(
        val title: String?,
        val level: Int?,
        val content: Collection<Content>,
        val images: Collection<Image>
)

data class Content(
        val type: String?,
        val text: String?
)


data class Image(
        val src: String?,
        val caption: String?
)
