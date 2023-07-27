package com.example.pokemontest.mvp.presenter

import com.example.pokemontest.di.list.ListScopeContainer
import com.example.pokemontest.mvp.model.IRepositoryList
import com.example.pokemontest.mvp.model.entity.ListPokemon
import com.example.pokemontest.mvp.model.entity.Pokemon
import com.example.pokemontest.mvp.presenter.itemPresenter.IPokemonItemPresenter
import com.example.pokemontest.mvp.view.PokemonListView
import com.example.pokemontest.mvp.view.itemView.IPokemonItemView
import com.example.pokemontest.navigation.IScreens
import com.example.pokemontest.utils.disposeBy
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.MvpPresenter
import javax.inject.Inject

class PokemonListPresenter : MvpPresenter<PokemonListView>() {

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var repositoryImpl: IRepositoryList

    @Inject
    lateinit var listScopeContainer: ListScopeContainer

    @Inject
    lateinit var uiScheduler: Scheduler

    @Inject
    lateinit var screen: IScreens

    private var disposable = CompositeDisposable()

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

    private var nextPageUrl: String? = null
    private var previousPageUrl: String? = null

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        viewState.init()

        loadData(null)

        pokemonItemPresenter.itemClickListener = {
            router.navigateTo(screen.pokemonDetailsScreen(pokemonItemPresenter.pokemons[it.pos]))
        }
    }

    fun loadData(limit: String?) {
        repositoryImpl.getList(limit).updateList()
    }

    fun nextPage() {
        repositoryImpl.nextPage(nextPageUrl).updateList()
    }

    fun previousPage() {
        repositoryImpl.previousPage(previousPageUrl).updateList()
    }

    private fun Single<ListPokemon>.updateList() {
        observeOn(uiScheduler)
            .subscribe({
                nextPageUrl = it.next
                previousPageUrl = it.previous
                viewState.showCount(it.count)
                pokemonItemPresenter.pokemons.clear()
                pokemonItemPresenter.pokemons.addAll(it.results)
                viewState.updateList()
            }, {

            }).disposeBy(disposable)
    }

    fun onBackPressed(): Boolean {
        router.exit()
        return true
    }

    override fun onDestroy() {
        listScopeContainer.releaseListSubcomponent()
        super.onDestroy()
        disposable.dispose()
    }
}