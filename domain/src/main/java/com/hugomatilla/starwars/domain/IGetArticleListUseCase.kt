package com.hugomatilla.starwars.domain

/**
 * Created by hugomatilla on 28/02/16.
 */

interface IGetArticleListUseCase {

    fun execute(): Collection<ArticleDomain>?
}