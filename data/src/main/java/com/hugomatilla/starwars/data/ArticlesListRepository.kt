package com.hugomatilla.starwars.data

import com.hugomatilla.starwars.data.cloud.Cloud
import com.hugomatilla.starwars.data.db.Db
import com.hugomatilla.starwars.domain.articles.IArticlesListRepository
import com.hugomatilla.starwars.domain.model.ArticleDomain
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.error

/**
 * Created by hugomatilla on 27/02/16.
 */

class ArticlesListRepository : IArticlesListRepository, AnkoLogger {

    // Todo, inject these dependencies
    val DB = Db()
    val CLOUD = Cloud()

    override fun getArticleList(): Collection<ArticleDomain>? {
        try {
            val articlesFromDb = DB.getArticleList()
            if (articlesFromDb.isNullOrEmpty()) {
                val articlesFromTheCloud = CLOUD.getArticleList()
                DB.saveArticles(articlesFromTheCloud)
                return DB.getArticleList()
            } else
                return articlesFromDb
        } catch(e: Exception) {
            error("Error in Repo: ${e.message}")
            throw e
        }
    }

    override fun deleteAll() {
        try {
            DB.clearDatabase()
        } catch(e: Exception) {
            error("Error while deleting DB: ${e.message}")
            throw e
        }
    }
}