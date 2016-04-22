package com.hugomatilla.starwars.data

import android.app.Application
import android.test.ApplicationTestCase
import com.hugomatilla.starwars.data.db.Db
import com.hugomatilla.starwars.data.db.DbHelper
import com.hugomatilla.starwars.data.db.model.DbMapper
import com.hugomatilla.starwars.domain.model.ArticleDomain

/**
 * [Testing Fundamentals](http://d.android.com/tools/testing/testing_android.html)
 */
//: ApplicationTestCase<Application>(Application::class.java) {
class DataIntegrationTest : ApplicationTestCase<MockApplication>(MockApplication::class.java) {
    private var app: Application? = null
    private var DB: Db? = null

    @Throws(Exception::class)
    override fun setUp() {
        super.setUp()
        createApplication()
        app = application
        DB = Db(DbHelper(app!!.applicationContext, DbHelper.DB_NAME_MOCK, 1), DbMapper())
    }

    fun test_saveArticle() {
        val article = ArticleDomain(1, "Title", "Abstract", "Thumbnail", "ThumbnailFull", 1, 2, "Type", "Url", null)
        DB!!.saveArticle(article)
        val result = DB!!.getArticleDetailById(1)
        if (result != null) {
            assertEquals(result, article)
        } else
            fail("Return value from the data base is null")
    }


    override fun tearDown() {
        DB!!.clearDatabase()
        super.tearDown()
    }
}
