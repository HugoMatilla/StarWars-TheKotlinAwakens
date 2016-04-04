package com.hugomatilla.starwars.domain.articles.list

import com.hugomatilla.starwars.domain.articles.IArticlesListRepository
import com.hugomatilla.starwars.domain.exception.DefaultError
import org.jetbrains.anko.async
import org.jetbrains.anko.uiThread

/**
 * Created by hugomatilla on 28/02/16.
 */


class GetArticlesListUseCase(
        val repository: IArticlesListRepository) :
        IGetArticlesListUseCase {

    override fun execute(callback: IGetArticlesListUseCase.Callback) {
        async() {
            try {
                val result = repository.getArticleList()
                uiThread { callback.onListLoaded(result) }
            } catch(e: Exception) {
                uiThread { callback.onError(DefaultError(e)) }
            }
        }
    }
}