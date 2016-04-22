package com.hugomatilla.starwars.domain.articles.list

import com.hugomatilla.starwars.domain.articles.IArticlesListRepository
import com.hugomatilla.starwars.domain.base.Command
import com.hugomatilla.starwars.domain.exception.DefaultError
import com.hugomatilla.starwars.domain.exception.ErrorBundle
import com.hugomatilla.starwars.domain.model.ArticleDomain

/**
 * Created by hugomatilla on 28/02/16.
 */


class GetArticlesListUseCase(val repository: IArticlesListRepository) :
        IGetArticlesListUseCase {

    override fun execute(callback: IGetArticlesListUseCase.Callback) {
        try {
            val result = repository.getArticleList()
            callback.onListLoaded(result)
        } catch(e: Exception) {
            callback.onError(DefaultError(e))
        }
    }
}

interface IGetArticlesListUseCase : Command<Collection<ArticleDomain>?, IGetArticlesListUseCase.Callback> {

    interface Callback {
        fun onListLoaded(articles: Collection<ArticleDomain>?)
        fun onError(error: ErrorBundle)
    }

    override fun execute(callback: Callback)
}