package com.example.pokemontest.mvp.presenter

import com.example.pokemontest.mvp.view.PokemonListView
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import javax.inject.Inject

class PokemonListPresenter : MvpPresenter<PokemonListView>() {

    @Inject
    lateinit var router: Router

    fun onBackPressed(): Boolean {
        router.exit()
        return true
    }
}