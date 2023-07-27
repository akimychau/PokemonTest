package com.example.pokemontest.di.details

import com.example.pokemontest.di.details.module.DetailsModule
import com.example.pokemontest.mvp.presenter.PokemonDetailsPresenter
import com.example.pokemontest.mvp.view.fragment.PokemonDetailsFragment
import dagger.Subcomponent

@DetailsScope
@Subcomponent(modules = [DetailsModule::class])
interface DetailsSubComponent {

    fun inject(pokemonDetailsPresenter: PokemonDetailsPresenter)
    fun inject(pokemonDetailsFragment: PokemonDetailsFragment)
}