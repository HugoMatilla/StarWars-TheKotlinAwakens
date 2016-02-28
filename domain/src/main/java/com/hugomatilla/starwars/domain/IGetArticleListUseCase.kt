package com.hugomatilla.starwars.domain

/**
 * Created by hugomatilla on 28/02/16.
 */

interface IGetArticleListUseCase {
    interface Callback {
        fun onListLoaded(articles: Collection<ArticleDomain>?)
        fun onError(error: String)
    }

    fun execute(callback: Callback)
}