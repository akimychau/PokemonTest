package com.example.pokemontest.navigation

import com.example.pokemontest.mvp.model.entity.Pokemon
import com.github.terrakok.cicerone.androidx.FragmentScreen

interface IScreens {

    fun pokemonListScreen(): FragmentScreen

    fun pokemonDetailsScreen(item: Pokemon): FragmentScreen
}