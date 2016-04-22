package com.hugomatilla.starwars.domain.articles.detail

import com.hugomatilla.starwars.domain.articles.IArticleDetailRepository
import com.hugomatilla.starwars.domain.base.Command
import com.hugomatilla.starwars.domain.exception.DefaultError
import com.hugomatilla.starwars.domain.exception.ErrorBundle
import com.hugomatilla.starwars.domain.model.ArticleDomain

/**
 * Created by hugomatilla on 28/02/16.
 */


class GetArticleDetailUseCase(
        val id: Int,
        val repository: IArticleDetailRepository) :
        IGetArticleDetailUseCase {

    override fun execute(callback: IGetArticleDetailUseCase.Callback) {
        try {
            val result = repository.getArticleDetail(id)
            callback.onArticleLoaded(result)
        } catch(e: Exception) {
            callback.onError(DefaultError(e))
        }
    }
}

interface IGetArticleDetailUseCase : Command<ArticleDomain?, IGetArticleDetailUseCase.Callback> {

    interface Callback {
        fun onArticleLoaded(article: ArticleDomain?)
        fun onError(error: ErrorBundle)
    }

    override fun execute(callback: Callback)
}
