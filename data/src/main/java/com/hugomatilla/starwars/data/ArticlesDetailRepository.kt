package com.hugomatilla.starwars.data

import com.hugomatilla.starwars.data.cloud.Cloud
import com.hugomatilla.starwars.data.db.Db
import com.hugomatilla.starwars.domain.articles.IArticleDetailRepository
import com.hugomatilla.starwars.domain.model.ArticleDomain
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.error

/**
 * Created by hugomatilla on 27/02/16.
 */

object ArticlesDetailRepository : IArticleDetailRepository, AnkoLogger {

    val DB = Db()
    val CLOUD = Cloud()

    override fun getArticleDetail(id: Int): ArticleDomain? {
        try {
            val articleFromDb = DB.getArticleDetailById(id)
            if (articleFromDb == null) {
                val articleFromTheCloud = CLOUD.getArticleDetailById(id)
                DB.saveArticle(articleFromTheCloud)
                return articleFromTheCloud
            } else if (articleFromDb.sections.isEmptyOrNull()) {
                val sectionsFromTheCloud = CLOUD.getArticleSections(id)
                articleFromDb.sections = sectionsFromTheCloud
                DB.saveArticle(articleFromDb)
                return articleFromDb
            } else {
                return articleFromDb
            }
        } catch(e: Exception) {
            error("Error in Repo: ${e.message} ")
            throw e
        }
    }
}