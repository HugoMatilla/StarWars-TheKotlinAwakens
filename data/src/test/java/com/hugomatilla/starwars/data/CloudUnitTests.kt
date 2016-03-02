package com.hugomatilla.starwars.data

import com.hugomatilla.starwars.data.cloud.RestService
import org.junit.Assert.assertNotNull
import org.junit.Test

/**
 * Created by hugomatilla on 02/03/16.
 */
class CloudUnitTest {
    @Test
    @Throws(Exception::class)
    fun requestCatchMalformedUrl() {
        val result = RestService().fetchArticlesList("htp://starwars.wikia.com/api/v1/" + RestService.Uris.topArticles)
        assertNotNull(result.error)
    }

    @Test
    @Throws(Exception::class)
    fun requestCatchJsonSyntaxException() {
        val result = RestService().fetchArticlesList("https://gist.githubusercontent.com/HugoMatilla/19d30910297dac9a2844/raw/eceadb974a61b7695ef6fee8501ac9c753a07a1a/MalformedJson.json")
        assertNotNull(result.error)
    }
}