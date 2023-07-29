package com.example.pokemontest.mvp.model.impl

import com.example.pokemontest.mvp.model.IRepositoryDetails
import com.example.pokemontest.mvp.model.api.IDataSource
import com.example.pokemontest.mvp.model.network.INetworkStatus
import io.reactivex.rxjava3.schedulers.Schedulers

class RepositoryDetailsImpl(
    private val api: IDataSource,
    private val networkStatus: INetworkStatus
) : IRepositoryDetails {

    override fun getPokemonDetails(uri: String) =
        networkStatus.isOnlineSingle().flatMap { hasConnection ->
            if (hasConnection) {
                api.getPokemonDetails(uri)
            } else {
                api.getPokemonDetails(uri)
            }
        }.subscribeOn(Schedulers.io())
}