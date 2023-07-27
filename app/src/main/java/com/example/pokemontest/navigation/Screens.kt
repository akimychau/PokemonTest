package com.example.pokemontest.navigation

import com.example.pokemontest.mvp.model.entity.Pokemon
import com.example.pokemontest.mvp.view.fragment.PokemonDetailsFragment
import com.example.pokemontest.mvp.view.fragment.PokemonListFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

class Screens : IScreens {

    override fun pokemonListScreen() = FragmentScreen { PokemonListFragment.instance() }

    override fun pokemonDetailsScreen(item: Pokemon) =
        FragmentScreen { PokemonDetailsFragment.instance(item) }
}