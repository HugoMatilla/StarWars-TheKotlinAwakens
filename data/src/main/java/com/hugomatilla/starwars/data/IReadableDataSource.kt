package com.hugomatilla.starwars.data

import com.hugomatilla.starwars.domain.model.ArticleDomain
import com.hugomatilla.starwars.domain.model.SectionDomain

/**
 * Created by hugomatilla on 28/02/16.
 */


interface IReadableDataSource {
    fun getArticleList(): Collection<ArticleDomain>?
    fun getArticleDetailById(id: Int): ArticleDomain?
    fun getArticleSections(id: Int): Collection<SectionDomain>?
}