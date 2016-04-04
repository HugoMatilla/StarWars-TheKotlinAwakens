package com.hugomatilla.starwars.domain.articles.detail

import com.hugomatilla.starwars.domain.articles.IArticleDetailRepository
import com.hugomatilla.starwars.domain.exception.DefaultError
import org.jetbrains.anko.async
import org.jetbrains.anko.uiThread

/**
 * Created by hugomatilla on 28/02/16.
 */


class GetArticleDetailUseCase(
        val id: Int,
        val repository: IArticleDetailRepository) :
        IGetArticleDetailUseCase {

    override fun execute(callback: IGetArticleDetailUseCase.Callback) {
        async() {
            try {
                val result = repository.getArticleDetail(id)
                uiThread { callback.onArticleLoaded(result) }
            } catch(e: Exception) {
                uiThread { callback.onError(DefaultError(e)) }
            }
        }
    }
}
