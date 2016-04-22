package com.hugomatilla.starwars.data.cloud

import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.hugomatilla.starwars.data.cloud.model.ArticleAbstractCloud
import java.lang.reflect.Type

/**
 * Created by hugomatilla on 22/04/16.
 */

class ArticleCloudDeserializer(val id: Int) : JsonDeserializer<ArticleAbstractCloud> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): ArticleAbstractCloud? {
        val root = json?.asJsonObject?.get("items")?.asJsonObject;
        val jsonStr = root?.get(id.toString())?.asJsonObject
        val article = Gson().fromJson(jsonStr, ArticleAbstractCloud::class.java)
        return article
    }
}
