package com.hugomatilla.starwars.domain.articles

import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

/**
 * Created by hugomatilla on 28/02/16.
 */


class GetArticlesListUseCase(
        val repository: IArticlesListRepository,
        val threadPool: ExecutorService = Executors.newFixedThreadPool(5)) :
        IGetArticlesListUseCase {

    override fun execute(callback: IGetArticlesListUseCase.Callback) {
        val future = threadPool.submit<GetArticlesResult> { repository.getArticleList() }
        val result = future.get()

        if (result.error == null)
            callback.onListLoaded(result.articles)
        else
            callback.onError(result.error)
    }
}