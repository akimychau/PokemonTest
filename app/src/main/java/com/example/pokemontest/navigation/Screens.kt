package com.example.pokemontest.navigation

import com.example.pokemontest.mvp.view.fragment.PokemonListFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

class Screens : IScreens {

    override fun pokemonListScreen() = FragmentScreen { PokemonListFragment.instance() }
}