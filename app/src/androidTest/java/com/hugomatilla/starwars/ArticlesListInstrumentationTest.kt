package com.hugomatilla.starwars

import android.app.Activity
import android.test.ActivityInstrumentationTestCase2
import com.hugomatilla.starwars.articles.list.ArticlesListActivity

/**
 * Created by hugomatilla on 22/04/16.
 */

class ArticlesListInstrumentationTest : ActivityInstrumentationTestCase2<ArticlesListActivity>(ArticlesListActivity::class.java) {
    private var mainActivity: Activity? = null

    @Throws(Exception::class)
    override fun setUp() {
        super.setUp()
        mainActivity = activity
    }

    fun test_ProgressBar() {
        assert(true)
    }


    override fun tearDown() {
        super.tearDown()

    }
}
