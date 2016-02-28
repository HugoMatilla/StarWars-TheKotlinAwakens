package com.hugomatilla.starwars.data

import com.hugomatilla.starwars.data.cloud.Cloud

/**
 * Created by hugomatilla on 27/02/16.
 */

class DataProvider(val sources: List<IDataSource> = DataProvider.SOURCES) {

    companion object {
        val SOURCES = listOf(Cloud())
    }

    fun requestSource(): IDataSource {
        return SOURCES[0]
    }
}
