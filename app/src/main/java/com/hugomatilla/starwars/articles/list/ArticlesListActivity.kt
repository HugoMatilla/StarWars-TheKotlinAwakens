package com.hugomatilla.starwars.articles.list

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.Toolbar
import android.view.View
import com.hugomatilla.starwars.R
import com.hugomatilla.starwars.articles.detail.ArticleDetailActivity
import com.hugomatilla.starwars.domain.model.ArticleDomain
import com.hugomatilla.starwars.widgets.ToolbarManager
import kotlinx.android.synthetic.main.articles_list_activity.*
import kotlinx.android.synthetic.main.articles_list_item.view.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.alert
import org.jetbrains.anko.find
import org.jetbrains.anko.toast

/**
 * Created by hugomatilla on 04/04/16.
 */

class ArticlesListActivity : AppCompatActivity(), ArticlesListPresenter.View, ToolbarManager, AnkoLogger {
    override val toolbar: Toolbar by lazy { find<Toolbar>(R.id.toolbar) }

    private val presenter: ArticlesListPresenter = ArticlesListPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.articles_list_activity)
        listView.layoutManager = GridLayoutManager(this, 2)
        initToolbarSettings(presenter)
        toolbar.title = getString(R.string.app_name)
        showLoading()
        presenter.getArticlesList()
    }

    override fun showList(articles: Collection<ArticleDomain>) {
        inflateArticles(articles as List<ArticleDomain>)
    }

    private fun inflateArticles(articles: List<ArticleDomain>) {
        listView.adapter = ArticlesListAdapter(articles, {
            article, view ->
            navigateToDetail(article, view)
        })
    }

    override fun clearList() {
        (listView.adapter as ArticlesListAdapter).articlesList = emptyList()
        listView.adapter.notifyDataSetChanged()
    }


    private fun navigateToDetail(it: ArticleDomain, view: View) {
        ArticleDetailActivity.start(this, view.articleListImageView, it.id, it.title.orEmpty(), it.thumbnail.orEmpty())
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

}