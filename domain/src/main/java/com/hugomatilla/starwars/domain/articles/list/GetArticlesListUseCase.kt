package com.hugomatilla.starwars.domain.articles.list

import com.hugomatilla.starwars.domain.articles.IArticlesListRepository
import com.hugomatilla.starwars.domain.exception.DefaultError

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