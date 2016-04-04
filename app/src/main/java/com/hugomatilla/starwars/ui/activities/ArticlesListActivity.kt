package com.hugomatilla.starwars.ui.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.hugomatilla.starwars.R
import com.hugomatilla.starwars.data.ArticlesListRepository
import com.hugomatilla.starwars.domain.articles.list.GetArticlesListUseCase
import com.hugomatilla.starwars.domain.articles.list.IGetArticlesListUseCase
import com.hugomatilla.starwars.domain.exception.ErrorBundle
import com.hugomatilla.starwars.domain.model.ArticleDomain
import kotlinx.android.synthetic.main.articles_list_activity.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class ArticlesListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.articles_list_activity)
        listView.layoutManager = LinearLayoutManager(this)
        progressBar.visibility = View.VISIBLE
        getArticlesAndInflate()

    }

    private fun getArticlesAndInflate() {
        GetArticlesListUseCase(ArticlesListRepository)
                .execute(object : IGetArticlesListUseCase.Callback {
                    override fun onListLoaded(articles: Collection<ArticleDomain>?) {
                        inflateArticles(articles as List<ArticleDomain>?)
                        progressBar.visibility = View.GONE
                    }

                    override fun onError(error: ErrorBundle) {
                        progressBar.visibility = View.GONE
                        alert(error.errorMessage, "Error") { positiveButton("OK") { } }.show()
                    }
                })
    }

    private fun inflateArticles(articles: List<ArticleDomain>?) {
        if (articles != null)
            listView.adapter = ArticlesListAdapter(articles,
                    { startActivity<ArticleDetailActivity>(ArticleDetailActivity.ID to it.id, ArticleDetailActivity.HEADER_IMAGE to it.thumbnailFull!!) }
            )
        else
            toast("No articles to show. :)")
    }
}
