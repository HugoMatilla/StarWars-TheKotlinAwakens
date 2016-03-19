package com.hugomatilla.starwars.domain.articles.detail

import com.hugomatilla.starwars.domain.articles.IArticleDetailRepository
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
            val result = repository.getArticleDetail(id)
            uiThread {
                if (result.error == null)
                    callback.onArticleLoaded(result.sections)
                else
                    callback.onError(result.error)
            }
        }
    }
}