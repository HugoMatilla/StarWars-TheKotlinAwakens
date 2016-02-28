package com.hugomatilla.starwars.data.cloud

import com.hugomatilla.starwars.data.IDataSource
import com.hugomatilla.starwars.domain.ArticleDomain

/**
 * Created by hugomatilla on 27/02/16.
 */

class Cloud : IDataSource {
    override fun getArticleList(): Collection<ArticleDomain>? {
        return Request().execute()
    }

}