package com.hugomatilla.starwars.domain.model

/**
 * Created by hugomatilla on 27/02/16.
 */
data class ArticleDetailDomain(
        val id: Int,
        val title: String?,
        val abstract: String?,
        val thumbnail: String?,
        val thumbnailFull: String?,
        val originalWidth: Int?,
        val originalHeight: Int?,
        val url: String?,
        val type: String?,
        val sections: Collection<SectionContent>?
)

data class SectionContent(
        val title: String?,
        val level: Int?,
        val text: String?,
        val image: String?,
        val caption: String?
)


