package com.example.pokemontest.mvp.presenter

import com.example.pokemontest.mvp.model.entity.Pokemon
import com.example.pokemontest.mvp.view.PokemonDetailsView
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import javax.inject.Inject

class PokemonDetailsPresenter(private val pokemon: Pokemon?) : MvpPresenter<PokemonDetailsView>() {

    @Inject
    lateinit var router: Router

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        pokemon?.let { viewState.showName(it.name) }
    }

    fun onBackPressed(): Boolean {
        router.exit()
        return true
    }
}