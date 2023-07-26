package com.example.pokemontest.mvp.model

import com.example.pokemontest.mvp.model.api.IDataSource
import com.example.pokemontest.mvp.model.entity.ListPokemon
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RepositoryImpl(private val api: IDataSource) : IRepository {


    override fun getList(): Single<ListPokemon> =
        api.getAllPokemon().subscribeOn(Schedulers.io())
}