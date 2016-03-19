package com.hugomatilla.starwars.domain.articles.list

import com.hugomatilla.starwars.domain.articles.IArticlesListRepository
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
            val result = repository.getArticleList()
            uiThread {
                if (result.error == null)
                    callback.onListLoaded(result.articles)
                else
                    callback.onError(result.error)
            }
        }


    }
}