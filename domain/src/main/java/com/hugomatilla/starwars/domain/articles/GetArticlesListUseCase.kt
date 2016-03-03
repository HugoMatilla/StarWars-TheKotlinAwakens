package com.hugomatilla.starwars.domain.articles

import java.util.concurrent.ExecutorService

/**
 * Created by hugomatilla on 28/02/16.
 */


class GetArticlesListUseCase(
        val repository: IArticlesListRepository,
        val threadPool: ExecutorService) :
        IGetArticlesListUseCase {

    override fun execute(callback: IGetArticlesListUseCase.Callback) {
        val future = threadPool.submit<GetArticlesListResult> { repository.getArticleList() }
        val result = future.get()

        if (result.error == null)
            callback.onListLoaded(result.articles)
        else
            callback.onError(result.error)
    }
}