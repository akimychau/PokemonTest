package com.example.pokemontest.mvp.presenter

import com.example.pokemontest.mvp.model.IRepository
import com.example.pokemontest.mvp.model.Pokemon
import com.example.pokemontest.mvp.presenter.itemPresenter.IPokemonItemPresenter
import com.example.pokemontest.mvp.view.PokemonListView
import com.example.pokemontest.mvp.view.itemView.IPokemonItemView
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import javax.inject.Inject

class PokemonListPresenter : MvpPresenter<PokemonListView>() {

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var repositoryImpl: IRepository

    class PokemonItemPresenter : IPokemonItemPresenter {

        val pokemons = mutableListOf<Pokemon>()

        override var itemClickListener: ((IPokemonItemView) -> Unit)? = null

        override fun bindView(view: IPokemonItemView) {
            val pokemon = pokemons[view.pos]
            view.setName(pokemon.name)
        }

        override fun getCount() = pokemons.size
    }

    val pokemonItemPresenter = PokemonItemPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        viewState.init()

        loadData()
    }

    private fun loadData() {

        val pokemons = repositoryImpl.getList()

        pokemonItemPresenter.pokemons.addAll(pokemons)

        viewState.updateList()
    }

    fun onBackPressed(): Boolean {
        router.exit()
        return true
    }
}