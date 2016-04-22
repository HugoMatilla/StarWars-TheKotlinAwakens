package com.hugomatilla.starwars.articles.detail

import com.hugomatilla.starwars.base.Presenter
import com.hugomatilla.starwars.data.ArticlesDetailRepository
import com.hugomatilla.starwars.data.isEmptyOrNull
import com.hugomatilla.starwars.domain.articles.detail.GetArticleDetailUseCase
import com.hugomatilla.starwars.domain.articles.detail.IGetArticleDetailUseCase
import com.hugomatilla.starwars.domain.exception.ErrorBundle
import com.hugomatilla.starwars.domain.model.ArticleDomain
import com.hugomatilla.starwars.domain.model.SectionDomain
import org.jetbrains.anko.async
import org.jetbrains.anko.uiThread

/**
 * Created by hugomatilla on 04/04/16.
 */

class ArticleDetailPresenter(view: View) : Presenter<ArticleDetailPresenter.View>(view), IArticleDetailPresenter {
    override fun getDetailArticle(id: Int) {
        async() {
            GetArticleDetailUseCase(id, ArticlesDetailRepository)
                    .execute(object : IGetArticleDetailUseCase.Callback {
                        override fun onArticleLoaded(article: ArticleDomain?) {
                            uiThread {
                                if (article == null || article.sections.isEmptyOrNull()) view.showEmptyCase()
                                else view.showArticle(article.sections!!)
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
        fun showArticle(sections: Collection<SectionDomain>)
    }
}


interface IArticleDetailPresenter {
    fun getDetailArticle(id: Int)
}
