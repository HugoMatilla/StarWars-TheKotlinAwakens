package com.hugomatilla.starwars.ui.activities

import android.app.Activity
import android.os.Bundle
import android.view.View
import com.hugomatilla.starwars.R
import com.hugomatilla.starwars.data.ArticlesDetailRepository
import com.hugomatilla.starwars.domain.articles.GetArticleDetailUseCase
import com.hugomatilla.starwars.domain.articles.IGetArticleDetailUseCase
import com.hugomatilla.starwars.domain.exception.ErrorBundle
import com.hugomatilla.starwars.domain.model.SectionContent
import kotlinx.android.synthetic.main.article_detail_activity.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.toast

class ArticleDetailActivity : Activity() {

    companion object {
        val ID = "ArticleDetailActivity.ID"
        val TITLE = "ArticleDetailActivity.TITLE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.article_detail_activity)

        val id = intent.getIntExtra(ID, 0)
        GetArticleDetailUseCase(id, ArticlesDetailRepository, JobExecutor.threadPool)
                .execute(object : IGetArticleDetailUseCase.Callback {
                    override fun onArticleLoaded(sections: Collection<SectionContent>?) {
                        inflateArticle(sections)
                        progressBar.visibility = View.GONE
                    }

                    override fun onError(error: ErrorBundle) {
                        progressBar.visibility = View.GONE
                        alert(error.errorMessage, "Error") { positiveButton("OK") { } }.show()
                    }

                })
    }

    private fun inflateArticle(sections: Collection<SectionContent>?) {
        if (sections != null)
            listView.adapter = ArticleDetailAdapter(sections)
        else
            toast("Sorry this article is empty. :)")
    }
}
