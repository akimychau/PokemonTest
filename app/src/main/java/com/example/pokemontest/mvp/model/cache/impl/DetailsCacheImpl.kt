package com.example.pokemontest.mvp.model.cache.impl

import com.example.pokemontest.mvp.model.cache.IDetailsCache
import com.example.pokemontest.mvp.model.database.AppDatabase
import com.example.pokemontest.mvp.model.entity.api.details.DetailsPokemon
import com.example.pokemontest.mvp.model.entity.api.details.DreamWorld
import com.example.pokemontest.mvp.model.entity.api.details.Front
import com.example.pokemontest.mvp.model.entity.api.details.Sprites
import com.example.pokemontest.mvp.model.entity.api.list.Pokemon
import com.example.pokemontest.mvp.model.entity.database.RoomDetailsPokemon
import io.reactivex.rxjava3.core.Single

class DetailsCacheImpl(private val db: AppDatabase) : IDetailsCache {

    override fun insertDetailsToDb(
        details: DetailsPokemon,
        pokemon: Pokemon
    ) = db.detailsDao().insert(
        RoomDetailsPokemon(
            details.id,
            details.name,
            details.sprites.other.dream_world.front_default,
            details.types,
            details.height,
            details.weight,
            pokemon.name
        )
    )

    override fun getDetailsFromDb(pokemon: Pokemon): Single<DetailsPokemon> =
        db.detailsDao().findForPokemon(pokemon.name).map { dbDetails ->
            DetailsPokemon(
                dbDetails.id,
                dbDetails.name,
                Sprites(DreamWorld(Front(dbDetails.image))),
                dbDetails.types,
                dbDetails.height,
                dbDetails.weight
            )
        }
}