package com.hugomatilla.starwars.data

import android.app.Application
import android.test.ApplicationTestCase
import com.hugomatilla.starwars.data.db.Db
import com.hugomatilla.starwars.data.db.DbHelper
import com.hugomatilla.starwars.data.db.model.DbMapper
import com.hugomatilla.starwars.domain.model.ArticleDomain
import com.hugomatilla.starwars.domain.model.SectionDomain

/**
 * [Testing Fundamentals](http://d.android.com/tools/testing/testing_android.html)
 */
class DataTests : ApplicationTestCase<Application>(Application::class.java) {
    private var DB: Db? = null

    @Throws(Exception::class)
    override fun setUp() {
        super.setUp()
        createApplication()
        DB = Db(DbHelper(application.applicationContext, DbHelper.DB_NAME_MOCK, 1), DbMapper())
    }

    fun test_saveAndGetArticle() {
        val section1 = SectionDomain("Title1", 1, "Text1", "Image1", "Caption1")
        val section2 = SectionDomain("Title2", 2, "Text2", "Image2", "Caption2")
        val article1 = ArticleDomain(1, "Title", "Abstract", "Thumbnail", 1, 2, "Url", "Type", listOf(section1, section2))
        DB!!.saveArticle(article1)
        val result = DB!!.getArticleDetailById(1)
        assertEquals(result, article1)
    }

    fun test_GetSections() {
        val section1 = SectionDomain("Title1", 1, "Text1", "Image1", "Caption1")
        val section2 = SectionDomain("Title2", 2, "Text2", "Image2", "Caption2")
        val article1 = ArticleDomain(1, "Title", "Abstract", "Thumbnail", 1, 2, "Url", "Type", listOf(section1, section2))
        DB!!.saveArticle(article1)
        val result = DB!!.getArticleSections(1)
        assertEquals(result, listOf(section1, section2))
    }


    fun test_saveAndGetArticles() {
        val section1 = SectionDomain("Title1", 1, "Text1", "Image1", "Caption1")
        val section2 = SectionDomain("Title2", 2, "Text2", "Image2", "Caption2")
        val article2 = ArticleDomain(2, "Title1", "Abstract1", "Thumbnail1", 1, 2, "Url1", "Type1", listOf(section1, section2))
        val article3 = ArticleDomain(3, "Title2", "Abstract2", "Thumbnail2", 3, 4, "Url2", "Type2", emptyList())

        DB!!.saveArticles(listOf(article2, article3))

        val result2 = DB!!.getArticleDetailById(2)
        assertEquals(result2, article2)

        val result3 = DB!!.getArticleDetailById(3)
        assertEquals(result3, article3)
    }

    fun testGetArticlesList() {
        val article1 = ArticleDomain(1, "Title", "Abstract", "Thumbnail", 1, 2, "Url", "Type", emptyList())
        val article2 = ArticleDomain(2, "Title1", "Abstract1", "Thumbnail1", 1, 2, "Url1", "Type1", emptyList())
        val article3 = ArticleDomain(3, "Title2", "Abstract2", "Thumbnail2", 3, 4, "Url2", "Type2", emptyList())
        DB!!.saveArticles(listOf(article1, article2, article3))
        val result4 = DB!!.getArticleList()
        assertEquals(result4?.size, 3)

    }

    override fun tearDown() {
        DB!!.clearDatabase()
        super.tearDown()
    }
}
