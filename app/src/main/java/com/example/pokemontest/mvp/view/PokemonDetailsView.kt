package com.example.pokemontest.mvp.view

import com.example.pokemontest.mvp.model.entity.details.DetailsPokemon
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface PokemonDetailsView : MvpView {

    fun init(pokemon: DetailsPokemon)
}