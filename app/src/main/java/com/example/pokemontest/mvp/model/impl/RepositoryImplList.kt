package com.example.pokemontest.mvp.model.impl

import com.example.pokemontest.mvp.model.IRepositoryList
import com.example.pokemontest.mvp.model.api.IDataSource
import com.example.pokemontest.mvp.model.entity.ListPokemon
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RepositoryImplList(private val api: IDataSource) : IRepositoryList {


    override fun getList(limit: String?): Single<ListPokemon> =
        api.getAllPokemon(limit).subscribeOn(Schedulers.io())

    override fun nextPage(next: String?) =
        api.nextPage(next).subscribeOn(Schedulers.io())

    override fun previousPage(previous: String?) =
        api.previousPage(previous).subscribeOn(Schedulers.io())
}