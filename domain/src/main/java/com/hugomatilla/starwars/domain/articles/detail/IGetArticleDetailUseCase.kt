package com.hugomatilla.starwars.domain.articles.detail

import com.hugomatilla.starwars.domain.base.Command
import com.hugomatilla.starwars.domain.exception.ErrorBundle
import com.hugomatilla.starwars.domain.model.ArticleDomain
import org.jetbrains.anko.async
import org.jetbrains.anko.uiThread

/**
 * Created by hugomatilla on 28/02/16.
 */

interface IGetArticleDetailUseCase : Command<ArticleDomain?, IGetArticleDetailUseCase.Callback> {

    interface Callback {
        fun onArticleLoaded(article: ArticleDomain?)
        fun onError(error: ErrorBundle)
    }

    override fun execute(callback: Callback)

    override fun execute(f: () -> ArticleDomain?, callback: IGetArticleDetailUseCase.Callback) {
        async() {
            val result = f()
            uiThread {
                callback.onArticleLoaded(result)
            }
        }
    }
}