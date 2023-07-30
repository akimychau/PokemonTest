package com.example.pokemontest.mvp.model.impl

import com.example.pokemontest.mvp.model.IRepositoryList
import com.example.pokemontest.mvp.model.api.IDataSource
import com.example.pokemontest.mvp.model.cache.IListCache
import com.example.pokemontest.mvp.model.network.INetworkStatus
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RepositoryListImpl(
    private val api: IDataSource,
    private val networkStatus: INetworkStatus,
    private val cache: IListCache
) : IRepositoryList {

    override fun getList(limit: String?) =
        networkStatus.isOnlineSingle().flatMap { hasConnection ->
            if (hasConnection) {
                api.getAllPokemon(limit).flatMap { list ->
                    cache.insertListToDb(list.results).andThen(Single.just(list))
                }
            } else {
                cache.getListFromDb()
            }
        }.subscribeOn(Schedulers.io())

    override fun nextPage(next: String?) =
        networkStatus.isOnlineSingle().flatMap { hasConnection ->
            if (hasConnection) {
                api.nextPage(next).flatMap { list ->
                    cache.insertListToDb(list.results).andThen(Single.just(list))
                }
            } else {
                cache.getListFromDb()
            }
        }.subscribeOn(Schedulers.io())

    override fun previousPage(previous: String?) =
        networkStatus.isOnlineSingle().flatMap { hasConnection ->
            if (hasConnection) {
                api.previousPage(previous).flatMap { list ->
                    cache.insertListToDb(list.results).andThen(Single.just(list))
                }
            } else {
                cache.getListFromDb()
            }
        }.subscribeOn(Schedulers.io())
}