package com.hugomatilla.starwars.domain.articles.list

import com.hugomatilla.starwars.domain.base.Command
import com.hugomatilla.starwars.domain.exception.ErrorBundle
import com.hugomatilla.starwars.domain.model.ArticleDomain
import org.jetbrains.anko.async
import org.jetbrains.anko.uiThread

/**
 * Created by hugomatilla on 28/02/16.
 */

interface IGetArticlesListUseCase : Command<Collection<ArticleDomain>?, IGetArticlesListUseCase.Callback> {

    interface Callback {
        fun onListLoaded(articles: Collection<ArticleDomain>?)
        fun onError(error: ErrorBundle)
    }

    override fun execute(callback: Callback)

    override fun execute(f: () -> Collection<ArticleDomain>?, callback: Callback) {
        async() {
            val result = f()
            uiThread {
                callback.onListLoaded(result)
            }
        }
    }
}