package com.hugomatilla.starwars.data

import com.hugomatilla.starwars.data.cloud.Cloud
import com.hugomatilla.starwars.data.db.Db

/**
 * Created by hugomatilla on 27/02/16.
 */

class DataProvider(val sources: List<IReadableDataSource> = DataProvider.SOURCES) {

    companion object {
        val SOURCES = listOf(Db(), Cloud())
    }

    fun requestSource(): IReadableDataSource {
        return SOURCES[0]
    }
}
