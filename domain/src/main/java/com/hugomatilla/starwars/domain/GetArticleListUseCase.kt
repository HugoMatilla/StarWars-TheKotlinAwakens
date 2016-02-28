package com.hugomatilla.starwars.domain

/**
 * Created by hugomatilla on 28/02/16.
 */


class GetArticleListUseCase(val repository: IArticleListRepository) : IGetArticleListUseCase {


    override fun execute(): Collection<ArticleDomain>? {
        return repository.getArticleList()
    }

}