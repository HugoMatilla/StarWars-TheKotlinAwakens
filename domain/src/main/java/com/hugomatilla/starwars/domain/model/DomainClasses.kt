package com.hugomatilla.starwars.domain2.model

/**
 * Created by hugomatilla on 27/02/16.
 */
data class ArticleDomain(
        val id: Int,
        val title: String?,
        val abstract: String?,
        val thumbnail: String?,
        val originalWidth: Int?,
        val originalHeight: Int?,
        val url: String?,
        val type: String?,
        var sections: Collection<SectionDomain>?
)

data class SectionDomain(
        val title: String?,
        val level: Int?,
        val text: String?,
        val image: String?,
        val caption: String?
)

