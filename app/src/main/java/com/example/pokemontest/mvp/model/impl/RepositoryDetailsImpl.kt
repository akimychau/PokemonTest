package com.example.pokemontest.mvp.model.impl

import com.example.pokemontest.mvp.model.IRepositoryDetails
import com.example.pokemontest.mvp.model.api.IDataSource
import com.example.pokemontest.mvp.model.cache.IDetailsCache
import com.example.pokemontest.mvp.model.entity.api.list.Pokemon
import com.example.pokemontest.mvp.model.network.INetworkStatus
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RepositoryDetailsImpl(
    private val api: IDataSource,
    private val networkStatus: INetworkStatus,
    private val cache: IDetailsCache
) : IRepositoryDetails {

    override fun getPokemonDetails(pokemon: Pokemon) =
        networkStatus.isOnlineSingle().flatMap { hasConnection ->
            if (hasConnection) {
                api.getPokemonDetails(pokemon.url).flatMap { details ->
                    cache.insertDetailsToDb(details, pokemon).andThen(Single.just(details))
                }
            } else {
                cache.getDetailsFromDb(pokemon)
            }
        }.subscribeOn(Schedulers.io())
}