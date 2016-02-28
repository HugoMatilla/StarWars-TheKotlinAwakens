package com.hugomatilla.starwars.data

import com.hugomatilla.starwars.domain.ArticleDomain

/**
 * Created by hugomatilla on 28/02/16.
 */

interface IDataSource {
    fun getArticleList(): Collection<ArticleDomain>?
}