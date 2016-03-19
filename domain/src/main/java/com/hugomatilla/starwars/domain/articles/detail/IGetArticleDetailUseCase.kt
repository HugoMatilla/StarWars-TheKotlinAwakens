package com.hugomatilla.starwars.domain.articles.detail

import com.hugomatilla.starwars.domain.base.Command
import com.hugomatilla.starwars.domain.exception.ErrorBundle
import com.hugomatilla.starwars.domain.model.SectionContent

/**
 * Created by hugomatilla on 28/02/16.
 */

interface IGetArticleDetailUseCase : Command<IGetArticleDetailUseCase.Callback> {

    interface Callback {
        fun onArticleLoaded(sections: Collection<SectionContent>?)
        fun onError(error: ErrorBundle)
    }

    override fun execute(callback: Callback)
}