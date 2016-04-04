package com.hugomatilla.starwars.data

import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.hugomatilla.starwars.data.cloud.RestService
import com.hugomatilla.starwars.data.cloud.model.CloudMapper
import com.hugomatilla.starwars.data.cloud.model.SectionsCloud
import org.junit.Assert.*
import org.junit.Test
import java.io.File
import java.net.MalformedURLException


/**
 * Created by hugomatilla on 02/03/16.
 */
class CloudUnitTest {
    companion object {
        val jsonFilePath = "/Users/hugomatilla/Documents/CodeBase/MatgomLibs/StarWars/data/src/test/java/com/hugomatilla/starwars/data/ArticleDetail.json"
        val jsonStr = File(jsonFilePath).readText(charset = Charsets.UTF_8)
    }

    @Test
    fun requestTopArticles() {
        val articles = RestService().fetchArticlesList()
        assertNotEquals(articles?.size, 0)
        assertEquals(articles?.elementAt(0)?.id, 4021)
    }

    @Test
    fun requestCatchMalformedUrl() {
        try {
            RestService().fetchArticlesList("htp://starwars.wikia.com/api/v1/" + RestService.Uris.TOP_ARTICLES_PART)
            fail("MalformedURLException not catch");
        } catch (error: MalformedURLException) {
        }
    }

    @Test
    fun requestCatchJsonSyntaxException() {
        try {
            RestService().fetchArticlesList("https://gist.githubusercontent.com/HugoMatilla/19d30910297dac9a2844/raw/eceadb974a61b7695ef6fee8501ac9c753a07a1a/MalformedJson.json")
            fail("JsonSyntaxException not catch");
        } catch (error: JsonSyntaxException) {
        }
    }

    @Test
    fun requestArticle() {
        //        val article = RestService().fetchArticleDetail(452217)
        //        assertNotNull(article)
        //        if (sections != null)
        //            assertEquals(sections.title, "Luke Skywalker")
    }

    @Test
    fun parseJsonDetailToArticleContent() {
        val content = Gson().fromJson(jsonStr, SectionsCloud::class.java)
        val sections = content.sections
        if (sections != null) {
            assertEquals(sections.elementAt(0).title, "Kylo Ren")
            assertEquals(sections.elementAt(1).title, "Biography")
            assertEquals(sections.elementAt(1).level, 2)
            assertEquals(sections.elementAt(2).title, "Early life")
            assertEquals(sections.elementAt(3).title, "First Order-Resistance conflict")
            assertEquals(sections.elementAt(4).content.size, 6)
            assertEquals(sections.elementAt(4).content.elementAt(0).type, "paragraph")
            assertEquals(sections.elementAt(4).content.elementAt(0).text!!.substring(4, 14), "Resistance")
        }
    }

    @Test
    fun convertArticleContentToDomain() {
        val cloudMapper = CloudMapper()
        val content = Gson().fromJson(jsonStr, SectionsCloud::class.java)
        val sections = cloudMapper.articleSectionsContentToDomain(content)
        if (sections != null) {
            assertEquals(sections.elementAt(0).title, "Kylo Ren")
            assertEquals(sections.elementAt(0).text!!.substring(1951), "completed.")
            assertEquals(sections.elementAt(1).title, "Biography")
            assertEquals(sections.elementAt(1).level, 2)
            assertEquals(sections.elementAt(2).title, "Early life")
            assertEquals(sections.elementAt(3).title, "First Order-Resistance conflict")
            assertEquals(sections.elementAt(4).image!!.substring(130), "20151231231227")
            assertEquals(sections.elementAt(4).caption!!.substring(0, 28), "Ren interrogates Poe Dameron")
        }
    }
}