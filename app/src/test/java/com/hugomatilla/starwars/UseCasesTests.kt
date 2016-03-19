package com.hugomatilla.starwars

import com.hugomatilla.starwars.data.ArticlesDetailRepository
import com.hugomatilla.starwars.data.ArticlesListRepository
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Created by hugomatilla on 18/03/16.
 */
class UseCasesTests {

    @Test
    fun getArticleListUseCase() {
        val repository = ArticlesListRepository
        val result = repository.getArticleList()
        assertEquals(result.articles?.elementAt(0)!!.id, 4021)
    }

    @Test
    fun getArticleDetailUseCase() {
        val repository = ArticlesDetailRepository
        val result = repository.getArticleDetail(472920)
        assertEquals(result.sections!!.elementAt(0).title, "Kylo Ren")
        assertEquals(result.sections!!.elementAt(0).text!!.substring(1951), " completed.")
        assertEquals(result.sections!!.elementAt(1).title, "Biography")
        assertEquals(result.sections!!.elementAt(1).level, 2)
        assertEquals(result.sections!!.elementAt(2).title, "Early life")
        assertEquals(result.sections!!.elementAt(3).title, "First Order-Resistance conflict")
        assertEquals(result.sections!!.elementAt(4).image!!.substring(130), "20151231231227")
        assertEquals(result.sections!!.elementAt(4).caption!!.substring(0, 28), "Ren interrogates Poe Dameron")
    }
}
