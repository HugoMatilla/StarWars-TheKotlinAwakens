package com.hugomatilla.starwars.data.db.model

/**
 * Created by hugomatilla on 19/03/16.
 */

object ArticleTable {
    val TABLE_NAME = "Article"
    val ID = "_id"
    val TITLE = "title"
    val ABSTRACT = "abstract"
    val THUMBNAIL = "thumbnail"
    val WIDTH = "width"
    val HEIGHT = "height"
    val TYPE = "type"
    val URL = "url"
}


object SectionTable {
    val TABLE_NAME = "Section"
    val ID = "_id"
    val TITLE = "title"
    val LEVEL = "level"
    val CONTENT = "content"
    val IMAGE = "image"
    val CAPTION = "caption"
    val ARTICLE_ID = "articleId"
}