package com.example.pokemontest.mvp.presenter

import com.example.pokemontest.mvp.model.IRepositoryDetails
import com.example.pokemontest.mvp.model.entity.api.list.Pokemon
import com.example.pokemontest.mvp.view.PokemonDetailsView
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import javax.inject.Inject

class PokemonDetailsPresenter(private val pokemon: Pokemon?) : MvpPresenter<PokemonDetailsView>() {

    @Inject
    lateinit var router: Router
    @Inject
    lateinit var repositoryDetailsImpl: IRepositoryDetails
    @Inject
    lateinit var uiScheduler: Scheduler

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadData()
    }

    private fun loadData(){
        pokemon?.let { repositoryDetailsImpl.getPokemonDetails(it).observeOn(uiScheduler)
            .subscribe({details ->
                viewState.init(details)
            },{

            })}
    }

    fun onBackPressed(): Boolean {
        router.exit()
        return true
    }
}