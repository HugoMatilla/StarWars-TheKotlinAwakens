package com.hugomatilla.starwars.data

import com.hugomatilla.starwars.data.cloud.RestService
import org.junit.Assert.*
import org.junit.Test

/**
 * Created by hugomatilla on 02/03/16.
 */
class CloudUnitTest {

    @Test
    fun requestTopArticles() {
        val result = RestService().fetchArticlesList()
        assertNotEquals(result.articles?.size, 0)
        assertEquals(result.articles?.elementAt(0)?.id, 472920)
    }

    @Test
    fun requestCatchMalformedUrl() {
        val result = RestService().fetchArticlesList("htp://starwars.wikia.com/api/v1/" + RestService.Uris.TOP_ARTICLES_PART)
        assertNotNull(result.error)
    }

    @Test
    fun requestCatchJsonSyntaxException() {
        val result = RestService().fetchArticlesList("https://gist.githubusercontent.com/HugoMatilla/19d30910297dac9a2844/raw/eceadb974a61b7695ef6fee8501ac9c753a07a1a/MalformedJson.json")
        assertNotNull(result.error)
    }

    @Test
    fun requestArticle() {
        val result = RestService().fetchArticleDetail(452217)
        val sections = result.sections
        assertNotNull(sections)
        if (sections != null)
            assertEquals(sections.elementAt(0).title, "Luke Skywalker")
    }
}