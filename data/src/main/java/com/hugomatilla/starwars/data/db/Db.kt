package com.hugomatilla.starwars.data.db

import com.hugomatilla.starwars.data.IReadableDataSource
import com.hugomatilla.starwars.data.db.model.*
import com.hugomatilla.starwars.data.toVarargArray
import com.hugomatilla.starwars.domain.model.ArticleDomain
import com.hugomatilla.starwars.domain.model.SectionDomain
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.db.replace
import org.jetbrains.anko.db.select
import org.jetbrains.anko.error

/**
 * Created by hugomatilla on 20/03/16.
 */

class Db(val dbHelper: DbHelper = DbHelper.instance,
         val dataMapper: DbMapper = DbMapper()) : IReadableDataSource, AnkoLogger {


    override fun getArticleList() = dbHelper.use {
        try {
            val articles = select(ArticleTable.TABLE_NAME).parseList(ArticleDb.parser)
            dataMapper.articlesFromDbToDomain(articles)
        } catch(e: Exception) {
            error("DB error ${e.message}")
            null
        }
    }

    override fun getArticleDetailById(id: Int) = dbHelper.use {
        try {
            val article = select(ArticleTable.TABLE_NAME)
                    .whereSimple("${ArticleTable.ID} = ?", id.toString()).
                    parseOpt(ArticleDb.parser)
            if (article != null) {
                val articleDb = with(article) {
                    ArticleDb(id.toLong(), title, abstract, thumbnail.orEmpty(), width, height, url, type)
                }
                val sectionsDomain = getArticleSections(article._id.toInt())
                dataMapper.articleFromDbToDomain(articleDb, getSectionsDb(article, sectionsDomain))
            } else
                null
        } catch(e: Exception) {
            error("DB error ${e.message}")
            throw e
        }
    }

    private fun getSectionsDb(article: ArticleDb, sectionsDomain: Collection<SectionDomain>?): List<SectionDb> {
        if (sectionsDomain != null)
            return dataMapper.sectionsFromDomainToDb(article._id.toInt(), sectionsDomain) as List<SectionDb>
        else
            return emptyList()
    }

    override fun getArticleSections(id: Int) = dbHelper.use {
        try {
            val sections = select(SectionTable.TABLE_NAME)
                    .whereSimple("${SectionTable.ARTICLE_ID} = ?", id.toString())
                    .parseList(SectionDb.parser)
            dataMapper.sectionsFromDbToDomain(sections)
        } catch(e: Exception) {
            error("DB sections error ${e.message}")
            throw e
        }
    }

    fun saveArticles(articles: Collection<ArticleDomain>?) = dbHelper.use {
        if (articles != null) {
            clear(ArticleTable.TABLE_NAME)
            val articlesDb = dataMapper.articlesFromDomainToDb(articles)
            articlesDb.forEach { replace(ArticleTable.TABLE_NAME, *it.map.toVarargArray()) }
        }
    }

    fun saveArticle(article: ArticleDomain?) = dbHelper.use {
        if (article != null) {
            val articlesDb = dataMapper.articleFromDomainToDb(article)
            replace(ArticleTable.TABLE_NAME, *articlesDb.map.toVarargArray())
            saveSections(article.id, article.sections)
        }
    }

    private fun saveSections(articleId: Int, sections: Collection<SectionDomain>?) = dbHelper.use {
        if (sections != null) {
            delete(SectionTable.TABLE_NAME, "${SectionTable.ARTICLE_ID} = ?", arrayOf(articleId.toString()))
            val sectionsDb = dataMapper.sectionsFromDomainToDb(articleId, sections)
            sectionsDb.forEach { replace(SectionTable.TABLE_NAME, *it.map.toVarargArray()) }
        }
    }
}