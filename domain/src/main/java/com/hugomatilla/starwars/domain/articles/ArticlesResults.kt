package com.hugomatilla.starwars.domain.articles

import com.hugomatilla.starwars.domain.exception.ErrorBundle
import com.hugomatilla.starwars.domain.model.ArticleDetailDomain
import com.hugomatilla.starwars.domain.model.SectionContent

/**
 * Created by hugomatilla on 01/03/16.
 */
data class GetArticlesListResult(
        val articles: Collection<ArticleDetailDomain>?,
        val error: ErrorBundle? = null
)

data class GetArticleDetailResult(
        val sections: Collection<SectionContent>?,
        val error: ErrorBundle? = null
)
