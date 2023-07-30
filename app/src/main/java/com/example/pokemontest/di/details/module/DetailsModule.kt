package com.example.pokemontest.di.details.module

import com.example.pokemontest.App
import com.example.pokemontest.di.details.DetailsScope
import com.example.pokemontest.di.details.DetailsScopeContainer
import com.example.pokemontest.mvp.model.IRepositoryDetails
import com.example.pokemontest.mvp.model.api.IDataSource
import com.example.pokemontest.mvp.model.cache.IDetailsCache
import com.example.pokemontest.mvp.model.cache.impl.DetailsCacheImpl
import com.example.pokemontest.mvp.model.database.AppDatabase
import com.example.pokemontest.mvp.model.impl.RepositoryDetailsImpl
import com.example.pokemontest.mvp.model.network.INetworkStatus
import dagger.Module
import dagger.Provides

@Module
class DetailsModule {

    @DetailsScope
    @Provides
    fun getPokemonDetails(
        api: IDataSource,
        networkStatus: INetworkStatus,
        cache: IDetailsCache
    ): IRepositoryDetails =
        RepositoryDetailsImpl(api, networkStatus, cache)

    @DetailsScope
    @Provides
    fun detailsCache(db: AppDatabase): IDetailsCache = DetailsCacheImpl(db)

    @DetailsScope
    @Provides
    fun scopeContainer(app: App): DetailsScopeContainer = app
}