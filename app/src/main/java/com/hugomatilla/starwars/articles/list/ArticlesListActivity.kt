package com.hugomatilla.starwars.articles.list

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.hugomatilla.starwars.R
import com.hugomatilla.starwars.articles.detail.ArticleDetailActivity
import com.hugomatilla.starwars.domain.model.ArticleDomain
import kotlinx.android.synthetic.main.articles_list_activity.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

/**
 * Created by hugomatilla on 04/04/16.
 */

class ArticlesListActivity : AppCompatActivity(), ArticlesListPresenter.View {


    private val presenter: ArticlesListPresenter = ArticlesListPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.articles_list_activity)
        listView.layoutManager = LinearLayoutManager(this)
        showLoading()
        presenter.getArticlesList()
    }

    override fun showList(articles: Collection<ArticleDomain>) {
        inflateArticles(articles as List<ArticleDomain>)
    }

    override fun showError(message: String) {
        alert(message) { positiveButton("OK") { } }.show()
    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = View.INVISIBLE
    }

    override fun showEmptyCase() {
        toast("No articles to show. :)")
    }

    private fun inflateArticles(articles: List<ArticleDomain>) {
        listView.adapter = ArticlesListAdapter(articles,
                {
                    startActivity<ArticleDetailActivity>(
                            ArticleDetailActivity.ID to it.id,
                            ArticleDetailActivity.HEADER_TITLE to it.title.orEmpty(),
                            ArticleDetailActivity.HEADER_IMAGE to it.thumbnailFull.orEmpty())
                })
    }
}