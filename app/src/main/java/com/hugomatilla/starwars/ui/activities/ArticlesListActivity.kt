package com.hugomatilla.starwars.ui.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.hugomatilla.starwars.R
import com.hugomatilla.starwars.data.ArticlesListRepository
import com.hugomatilla.starwars.domain.articles.GetArticlesListUseCase
import com.hugomatilla.starwars.domain.articles.IGetArticlesListUseCase
import com.hugomatilla.starwars.domain.exception.ErrorBundle
import com.hugomatilla.starwars.domain.model.ArticleDetailDomain
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

    }

    override fun onResume() {
        super.onResume()
        GetArticlesListUseCase(ArticlesListRepository, JobExecutor.threadPool)
                .execute(object : IGetArticlesListUseCase.Callback {
                    override fun onListLoaded(articles: Collection<ArticleDetailDomain>?) {
                        inflateArticles(articles)
                        progressBar.visibility = View.GONE
                    }

                    override fun onError(error: ErrorBundle) {
                        progressBar.visibility = View.GONE
                        alert(error.errorMessage, "Error") { positiveButton("OK") { } }.show()
                    }
                })
    }

    private fun inflateArticles(articles: Collection<ArticleDetailDomain>?) {
        if (articles != null)
            listView.adapter = ArticlesListAdapter(articles,
                    //                    { Log.d(this.javaClass.canonicalName, it.toString()) }
                    { startActivity<ArticleDetailActivity>(Pair(ArticleDetailActivity.ID, it.id)) }
            )
        else
            toast("No articles to show. :)")
    }

}
