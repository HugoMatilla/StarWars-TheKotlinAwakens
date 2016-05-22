package com.hugomatilla.starwars.articles.detail

import android.app.Activity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.view.View
import com.hugomatilla.starwars.R
import com.hugomatilla.starwars.domain2.model.SectionDomain
import com.hugomatilla.starwars.widgets.ToolbarManager
import kotlinx.android.synthetic.main.article_detail_activity.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.find
import org.jetbrains.anko.toast

class ArticleDetailActivity : Activity(), ArticleDetailPresenter.View, ToolbarManager {

    override val toolbar: Toolbar by lazy { find<Toolbar>(R.id.toolbar) }


    companion object {
        val ID = "ArticleDetailActivity.ID"
        val HEADER_IMAGE = "ArticleDetailActivity.HEADER_IMAGE"
        val HEADER_TITLE = "ArticleDetailActivity.HEADER_TITLE"
    }

    val headerImage: String by lazy { intent.getStringExtra(HEADER_IMAGE) }
    private val presenter: ArticleDetailPresenter = ArticleDetailPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.article_detail_activity)
        toolbar.title = intent.getStringExtra(HEADER_TITLE)
        enableHomeAsUp { onBackPressed() }
        sectionsListView.layoutManager = LinearLayoutManager(this)
        val id = intent.getIntExtra(ID, 0)
        presenter.getDetailArticle(id)
    }

    override fun showArticle(sections: Collection<SectionDomain>) {
        val sectionsWithHeader = addArticleHeaderInfo(sections)
        sectionsListView.adapter = ArticleDetailAdapter(sectionsWithHeader)
    }

    override fun showError(message: String) {
        alert(message) { positiveButton("OK") { } }.show()
    }

    override fun showEmptyCase() {
        toast("Sorry this article is empty. :)")
    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = View.INVISIBLE
    }


    private fun addArticleHeaderInfo(sections: Collection<SectionDomain>): Collection<SectionDomain> {
        val sectionsMutable = sections.toMutableList()
        val headerSection = with(sections.elementAt(0)) { SectionDomain(title, level, text, headerImage, caption) }
        sectionsMutable.removeAt(0)
        sectionsMutable.add(0, headerSection)
        return sectionsMutable.toList()
    }
}
