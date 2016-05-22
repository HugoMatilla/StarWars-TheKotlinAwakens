package com.hugomatilla.starwars.domain2.articles.list

import com.hugomatilla.starwars.domain2.articles.IArticlesListRepository
import com.hugomatilla.starwars.domain2.exception.DefaultError
import com.hugomatilla.starwars.domain2.exception.ErrorBundle

/**
 * Created by hugomatilla on 11/04/16.
 */

class DeleteAllArticlesUseCase(val repository: IArticlesListRepository) :
        IDeleteAllArticlesUseCase {

    override fun execute(callback: IDeleteAllArticlesUseCase.Callback) {
        try {
            repository.deleteDatabase()
            callback.onArticlesDeleted()
        } catch(e: Exception) {
            callback.onError(DefaultError(e))
        }
    }
}

interface IDeleteAllArticlesUseCase {
    interface Callback {
        fun onArticlesDeleted()
        fun onError(error: ErrorBundle)
    }

    fun execute(callback: Callback)

}
