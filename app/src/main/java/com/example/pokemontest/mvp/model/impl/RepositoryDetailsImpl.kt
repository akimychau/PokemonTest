package com.example.pokemontest.mvp.model.impl

import com.example.pokemontest.mvp.model.IRepositoryDetails
import com.example.pokemontest.mvp.model.api.IDataSource
import io.reactivex.rxjava3.schedulers.Schedulers

class RepositoryDetailsImpl(private val api: IDataSource) : IRepositoryDetails {

    override fun getPokemonDetails(uri: String) =
        api.getPokemonDetails(uri).subscribeOn(Schedulers.io())
}