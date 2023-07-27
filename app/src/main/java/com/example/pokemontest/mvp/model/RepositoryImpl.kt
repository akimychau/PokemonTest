package com.example.pokemontest.mvp.model

import com.example.pokemontest.mvp.model.api.IDataSource
import com.example.pokemontest.mvp.model.entity.ListPokemon
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RepositoryImpl(private val api: IDataSource) : IRepository {


    override fun getList(limit: String?): Single<ListPokemon> =
        api.getAllPokemon(limit).subscribeOn(Schedulers.io())

    override fun nextPage(next: String?, limit: String?) =
        api.nextPage(next, limit).subscribeOn(Schedulers.io())

    override fun previousPage(previous: String?, limit: String?) =
        api.previousPage(previous, limit).subscribeOn(Schedulers.io())
}