package com.example.pokemontest.mvp.model.cache

import com.example.pokemontest.mvp.model.entity.api.list.ListPokemon
import com.example.pokemontest.mvp.model.entity.api.list.Pokemon
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface IListCache {

    fun insertListToDb(list: List<Pokemon>): Completable
    fun getListFromDb(): Single<ListPokemon>
}