package com.hugomatilla.starwars.domain.articles.detail

import com.hugomatilla.starwars.domain.articles.IArticleDetailRepository
import com.hugomatilla.starwars.domain.exception.DefaultError

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
