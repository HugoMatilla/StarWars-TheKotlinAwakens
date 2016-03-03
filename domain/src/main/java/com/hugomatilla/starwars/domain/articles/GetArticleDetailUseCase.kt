package com.hugomatilla.starwars.domain.articles

import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

/**
 * Created by hugomatilla on 28/02/16.
 */


class GetArticleDetailUseCase(
        val id: Int,
        val repository: IArticleDetailRepository,
        val threadPool: ExecutorService = Executors.newFixedThreadPool(5)) :
        IGetArticleDetailUseCase {

    override fun execute(callback: IGetArticleDetailUseCase.Callback) {
        val future = threadPool.submit<GetArticleDetailResult> { repository.getArticleDetail(id) }
        val result = future.get()

        if (result.error == null)
            callback.onArticleLoaded(result.sections)
        else
            callback.onError(result.error)
    }
}