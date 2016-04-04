package com.hugomatilla.starwars.data.db.model

import org.jetbrains.anko.db.rowParser
import java.util.*

/**
 * Created by hugomatilla on 27/02/16.
 */


class ArticleDb(val map: MutableMap<String, Any?>, var sections: Collection<SectionDb>?) {
    var _id: Long by map
    var title: String by map
    var abstract: String by map
    var thumbnail: String by map
    var width: Int by map
    var height: Int by map
    var type: String by map
    var url: String by map

    constructor(id: Long, title: String, abstract: String, thumbnail: String, width: Int, height: Int, type: String, url: String, sections: Collection<SectionDb>?)
    : this(HashMap(), sections) {
        this._id = id
        this.title = title
        this.abstract = abstract
        this.thumbnail = thumbnail
        this.width = width
        this.height = height
        this.type = type
        this.url = url
        this.sections = sections
    }

    constructor(id: Long, title: String, abstract: String, thumbnail: String, width: Int, height: Int, type: String, url: String)
    : this(HashMap(), null) {
        this._id = id
        this.title = title
        this.abstract = abstract
        this.thumbnail = thumbnail
        this.width = width
        this.height = height
        this.type = type
        this.url = url
    }

    companion object {
        val parser = rowParser { id: Long, title: String, abstract: String, thumbnail: String, width: Int, height: Int, type: String, url: String ->
            ArticleDb(id, title, abstract, thumbnail, width, height, url, type)
        }
    }
}


class SectionDb(val map: MutableMap<String, Any?>) {
    var title: String by map
    var level: Int by map
    var content: String by map
    var image: String by map
    var caption: String by map
    var articleId: Int by map

    constructor(title: String, level: Int, content: String, image: String, caption: String, articleId: Int)
    : this(HashMap()) {
        this.title = title
        this.level = level
        this.content = content
        this.image = image
        this.caption = caption
        this.articleId = articleId
    }

    companion object {
        val parser = rowParser { id: Long, title: String, level: Int, content: String, image: String, caption: String, articleId: Int ->
            SectionDb(title, level, content, image, caption, articleId)
        }
    }
}
