package com.example.pokemontest.mvp.model.cache.impl

import com.example.pokemontest.mvp.model.cache.IListCache
import com.example.pokemontest.mvp.model.database.AppDatabase
import com.example.pokemontest.mvp.model.entity.api.list.ListPokemon
import com.example.pokemontest.mvp.model.entity.api.list.Pokemon
import com.example.pokemontest.mvp.model.entity.database.RoomListPokemon

class ListCacheImpl(private val db: AppDatabase) : IListCache {

    override fun insertListToDb(list: List<Pokemon>) =
        db.listDao().insertAll(list.map {
            RoomListPokemon(
                it.name,
                it.url
            )
        })

    override fun getListFromDb() =
        db.listDao().getAllPokemon().map { pokemon ->
            val list = pokemon.map {
                Pokemon(
                    it.name,
                    it.url
                )
            }
            ListPokemon(
                list.size,
                null,
                null,
                list
            )
        }
}