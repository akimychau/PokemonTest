package com.example.pokemontest.di.details.module

import com.example.pokemontest.App
import com.example.pokemontest.di.details.DetailsScope
import com.example.pokemontest.di.details.DetailsScopeContainer
import com.example.pokemontest.mvp.model.IRepositoryDetails
import com.example.pokemontest.mvp.model.impl.RepositoryDetailsImpl
import dagger.Module
import dagger.Provides

@Module
class DetailsModule {

    @DetailsScope
    @Provides
    fun getPokemonDetails(): IRepositoryDetails = RepositoryDetailsImpl()

    @DetailsScope
    @Provides
    fun scopeContainer(app: App): DetailsScopeContainer = app
}