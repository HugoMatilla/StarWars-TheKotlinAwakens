package com.hugomatilla.starwars.domain

import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

/**
 * Created by hugomatilla on 28/02/16.
 */


class GetArticleListUseCase(
        val repository: IArticleListRepository,
        val threadPool: ExecutorService = Executors.newFixedThreadPool(5)) : IGetArticleListUseCase {


    override fun execute(callback: IGetArticleListUseCase.Callback) {
        val future = threadPool.submit<Collection<ArticleDomain>?> { repository.getArticleList() }
        val articles = future.get()
        callback.onListLoaded(articles)
        //        The callback runs on the thread the future was created, so in the mainThread. Using the uiThread interface is not needed in this case
        //        uiThread.post(Runnable { callback.onListLoaded(futureArticles) })
    }
}