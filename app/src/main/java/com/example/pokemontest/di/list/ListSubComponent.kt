package com.example.pokemontest.di.list

import com.example.pokemontest.di.list.module.ListModule
import com.example.pokemontest.mvp.presenter.PokemonListPresenter
import com.example.pokemontest.mvp.view.adapter.PokemonRVAdapter
import dagger.Subcomponent

@ListScope
@Subcomponent(modules = [ListModule::class])
interface ListSubComponent {

    fun inject(pokemonListPresenter: PokemonListPresenter)
    fun inject(pokemonRVAdapter: PokemonRVAdapter)
}