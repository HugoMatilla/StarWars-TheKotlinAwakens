package com.hugomatilla.starwars.ui.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.hugomatilla.starwars.R
import com.hugomatilla.starwars.data.cloud.ArticleRepository
import com.hugomatilla.starwars.domain.GetArticleListUseCase
import org.jetbrains.anko.async
import org.jetbrains.anko.find
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread

class ArticlesListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.articles_list_activity)
        val listView: RecyclerView = find(R.id.main_list)
        listView.layoutManager = LinearLayoutManager(this)

        async() {
            val articleRepository = ArticleRepository
            val result = GetArticleListUseCase(articleRepository).execute()
            uiThread {
                if (result != null)
                    listView.adapter = ArticlesListAdapter(result, { toast(it.title) })
                else
                    toast("No articles to show. :)")
            }
        }
    }
}
