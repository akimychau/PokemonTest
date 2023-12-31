package com.example.pokemontest.di.list.module

import com.example.pokemontest.App
import com.example.pokemontest.di.list.ListScope
import com.example.pokemontest.di.list.ListScopeContainer
import com.example.pokemontest.mvp.model.IRepositoryList
import com.example.pokemontest.mvp.model.api.IDataSource
import com.example.pokemontest.mvp.model.cache.IListCache
import com.example.pokemontest.mvp.model.cache.impl.ListCacheImpl
import com.example.pokemontest.mvp.model.database.AppDatabase
import com.example.pokemontest.mvp.model.impl.RepositoryListImpl
import com.example.pokemontest.mvp.model.network.INetworkStatus
import dagger.Module
import dagger.Provides

@Module
class ListModule {

    @ListScope
    @Provides
    fun getList(
        api: IDataSource,
        networkStatus: INetworkStatus,
        cache: IListCache
    ): IRepositoryList =
        RepositoryListImpl(api, networkStatus, cache)

    @ListScope
    @Provides
    fun listCache(db: AppDatabase): IListCache = ListCacheImpl(db)

    @ListScope
    @Provides
    fun scopeContainer(app: App): ListScopeContainer = app
}