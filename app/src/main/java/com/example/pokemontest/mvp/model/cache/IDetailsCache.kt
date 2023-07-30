package com.example.pokemontest.mvp.model.cache

import com.example.pokemontest.mvp.model.entity.api.details.DetailsPokemon
import com.example.pokemontest.mvp.model.entity.api.list.Pokemon
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface IDetailsCache {

    fun insertDetailsToDb(details: DetailsPokemon, pokemon: Pokemon): Completable
    fun getDetailsFromDb(pokemon: Pokemon): Single<DetailsPokemon>
}