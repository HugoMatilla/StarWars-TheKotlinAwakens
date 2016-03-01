package com.hugomatilla.starwars.domain.articles

import com.hugomatilla.starwars.domain.exception.ErrorBundle
import com.hugomatilla.starwars.domain.model.ArticleDomain

/**
 * Created by hugomatilla on 01/03/16.
 */
data class GetArticlesResult(
        val articles: Collection<ArticleDomain>?,
        val error: ErrorBundle? = null
)
