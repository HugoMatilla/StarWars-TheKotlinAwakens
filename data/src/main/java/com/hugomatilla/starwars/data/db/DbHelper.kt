package com.hugomatilla.starwars.data.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.hugomatilla.starwars.data.db.model.ArticleTable
import com.hugomatilla.starwars.data.db.model.SectionTable
import org.jetbrains.anko.db.*

/**
 * Created by hugomatilla on 19/03/16.
 */

class DbHelper(ctx: Context, name: String = DbHelper.DB_NAME, version: Int = DbHelper.DB_VERSION) :
        ManagedSQLiteOpenHelper(ctx, name, null, version) {

    companion object {
        val DB_NAME = "starwars.db"
        val DB_NAME_MOCK = "starwars.test.db"
        val DB_VERSION = 1
        lateinit var instance: DbHelper
            private set

        fun init(ctx: Context) {
            instance = DbHelper(ctx)
        }

    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(ArticleTable.TABLE_NAME, true,
                ArticleTable.ID to INTEGER + PRIMARY_KEY,
                ArticleTable.TITLE to TEXT,
                ArticleTable.ABSTRACT to TEXT,
                ArticleTable.THUMBNAIL to TEXT,
                ArticleTable.WIDTH to INTEGER,
                ArticleTable.HEIGHT to INTEGER,
                ArticleTable.TYPE to TEXT,
                ArticleTable.URL to TEXT)

        db.createTable(SectionTable.TABLE_NAME, true,
                SectionTable.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                SectionTable.TITLE to TEXT,
                SectionTable.LEVEL to INTEGER,
                SectionTable.CONTENT to TEXT,
                SectionTable.IMAGE to TEXT,
                SectionTable.CAPTION to TEXT,
                SectionTable.ARTICLE_ID to INTEGER)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(ArticleTable.TABLE_NAME, true)
        db.dropTable(SectionTable.TABLE_NAME, true)
        onCreate(db)
    }
}
