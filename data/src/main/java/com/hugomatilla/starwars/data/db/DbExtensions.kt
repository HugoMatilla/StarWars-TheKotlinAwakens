package com.hugomatilla.starwars.data.db

import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.MapRowParser
import org.jetbrains.anko.db.SelectQueryBuilder

/**
 * Created by hugomatilla on 20/03/16.
 */

fun SQLiteDatabase.clear(tableName: String) {
    execSQL("delete from $tableName")
}

fun <T : Any> SelectQueryBuilder.parseList(
        parser: (Map<String, Any>) -> T): List<T> =
        parseList(object : MapRowParser<T> {
            override fun parseRow(columns: Map<String, Any>): T = parser(columns)
        })