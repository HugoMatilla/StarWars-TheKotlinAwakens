package com.hugomatilla.starwars.ui.activities

import android.app.Activity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.hugomatilla.starwars.R
import com.hugomatilla.starwars.data.ArticlesDetailRepository
import com.hugomatilla.starwars.domain.articles.detail.GetArticleDetailUseCase
import com.hugomatilla.starwars.domain.articles.detail.IGetArticleDetailUseCase
import com.hugomatilla.starwars.domain.exception.ErrorBundle
import com.hugomatilla.starwars.domain.model.ArticleDomain
import com.hugomatilla.starwars.domain.model.SectionDomain
import kotlinx.android.synthetic.main.article_detail_activity.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.toast

class ArticleDetailActivity : Activity() {

    companion object {
        val ID = "ArticleDetailActivity.ID"
        val HEADER_IMAGE = "ArticleDetailActivity.HEADER_IMAGE"
    }

    val headerImage: String by lazy { intent.getStringExtra(HEADER_IMAGE) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.article_detail_activity)
        sectionsListView.layoutManager = LinearLayoutManager(this)

        val id = intent.getIntExtra(ID, 0)

        GetArticleDetailUseCase(id, ArticlesDetailRepository)
                .execute(object : IGetArticleDetailUseCase.Callback {
                    override fun onArticleLoaded(article: ArticleDomain?) {
                        inflateArticle(article?.sections)
                        progressBar.visibility = View.GONE
                    }

                    override fun onError(error: ErrorBundle) {
                        progressBar.visibility = View.GONE
                        alert(error.errorMessage, "Error") { positiveButton("OK") { } }.show()
                    }
                })
    }

    private fun inflateArticle(sections: Collection<SectionDomain>?) {

        if (sections != null) {
            val sectionsWithHeader = addArticleHeaderInfo(sections)
            sectionsListView.adapter = ArticleDetailAdapter(sectionsWithHeader)
        } else
            toast("Sorry this article is empty. :)")
    }

    private fun addArticleHeaderInfo(sections: Collection<SectionDomain>): Collection<SectionDomain> {
        var sectionsMutable = sections.toMutableList()
        var headerSection = with(sections.elementAt(0)) { SectionDomain(title, level, text, headerImage, caption) }
        sectionsMutable.removeAt(0)
        sectionsMutable.add(0, headerSection)
        return sectionsMutable.toList()
    }
}
