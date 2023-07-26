package com.example.pokemontest.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface PokemonListView : MvpView {

    fun init()
    fun updateList()
}