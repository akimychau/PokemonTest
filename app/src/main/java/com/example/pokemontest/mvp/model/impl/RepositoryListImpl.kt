package com.example.pokemontest.mvp.model.impl

import com.example.pokemontest.mvp.model.IRepositoryList
import com.example.pokemontest.mvp.model.api.IDataSource
import com.example.pokemontest.mvp.model.cache.IListCache
import com.example.pokemontest.mvp.model.entity.api.list.ListPokemon
import com.example.pokemontest.mvp.model.network.INetworkStatus
import com.example.pokemontest.utils.doCompletable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RepositoryListImpl(
    private val api: IDataSource,
    private val networkStatus: INetworkStatus,
    private val cache: IListCache
) : IRepositoryList {

    override fun getList(limit: String?) =
        getListByNetworkStatus(api.getAllPokemon(limit))

    override fun nextPage(next: String?) =
        getListByNetworkStatus(api.nextPage(next))

    override fun previousPage(previous: String?) =
        getListByNetworkStatus(api.previousPage(previous))

    private fun getListByNetworkStatus(list: Single<ListPokemon>): Single<ListPokemon> =
        networkStatus.isOnlineSingle().flatMap { hasConnection ->
            if (hasConnection) {
                list.doCompletable {
                    cache.insertListToDb(it.results)
                }
            } else {
                cache.getListFromDb()
            }
        }.subscribeOn(Schedulers.io())
}