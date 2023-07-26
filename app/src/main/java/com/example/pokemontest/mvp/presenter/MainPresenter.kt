package com.example.pokemontest.mvp.presenter

import com.example.pokemontest.mvp.view.MainView
import com.example.pokemontest.navigation.IScreens
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import javax.inject.Inject

class MainPresenter : MvpPresenter<MainView>() {

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var screen: IScreens

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(screen.pokemonListScreen())
    }

    fun onBackPressed() {
        router.exit()
    }
}