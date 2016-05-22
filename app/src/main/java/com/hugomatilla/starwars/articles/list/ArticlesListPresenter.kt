package com.hugomatilla.starwars.articles.list

import com.hugomatilla.starwars.base.Presenter
import com.hugomatilla.starwars.data.ArticlesListRepository
import com.hugomatilla.starwars.data.isEmptyOrNull
import com.hugomatilla.starwars.domain2.articles.list.DeleteAllArticlesUseCase
import com.hugomatilla.starwars.domain2.articles.list.GetArticlesListUseCase
import com.hugomatilla.starwars.domain2.articles.list.IDeleteAllArticlesUseCase
import com.hugomatilla.starwars.domain2.articles.list.IGetArticlesListUseCase
import com.hugomatilla.starwars.domain2.exception.ErrorBundle
import com.hugomatilla.starwars.domain2.model.ArticleDomain
import org.jetbrains.anko.async
import org.jetbrains.anko.uiThread

/**
 * Created by hugomatilla on 04/04/16.
 */

class ArticlesListPresenter(view: ArticlesListPresenter.View) : Presenter<ArticlesListPresenter.View>(view), IArticlesListPresenter {
    override fun getArticlesList() {
        async() {
            GetArticlesListUseCase(ArticlesListRepository).execute(object : IGetArticlesListUseCase.Callback {
                override fun onListLoaded(articles: Collection<ArticleDomain>?) {
                    uiThread {
                        if (articles.isEmptyOrNull())
                            view.showEmptyCase()
                        else
                            view.showList(articles!!)
                        view.hideLoading()
                    }
                }

                override fun onError(error: ErrorBundle) {
                    uiThread {
                        view.hideLoading()
                        view.showError(error.errorMessage)
                    }
                }
            })
        }
    }

    interface View : Presenter.View {
        fun showList(articles: Collection<ArticleDomain>)
    }

    //Todo move to its own presenter
    override fun deleteDatabase() {
        async() {
            DeleteAllArticlesUseCase(ArticlesListRepository).execute(object : IDeleteAllArticlesUseCase.Callback {
                override fun onArticlesDeleted() {
                    uiThread {
                        view.hideLoading()
                    }
                }

                override fun onError(error: ErrorBundle) {
                    uiThread {
                        view.hideLoading()
                        view.showError(error.errorMessage)
                    }
                }
            })
        }
    }
}

interface IArticlesListPresenter {
    fun getArticlesList()
    fun deleteDatabase()
}
