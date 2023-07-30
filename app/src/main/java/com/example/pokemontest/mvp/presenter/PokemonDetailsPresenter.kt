package com.example.pokemontest.mvp.presenter

import android.util.Log
import com.example.pokemontest.mvp.model.IRepositoryDetails
import com.example.pokemontest.mvp.model.entity.api.details.DetailsPokemon
import com.example.pokemontest.mvp.model.entity.api.list.Pokemon
import com.example.pokemontest.mvp.view.PokemonDetailsView
import com.example.pokemontest.utils.FIRST_ELEMENT
import com.example.pokemontest.utils.SECOND_ELEMENT
import com.example.pokemontest.utils.disposeBy
import com.example.pokemontest.utils.firstCharToUpperCase
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.MvpPresenter
import javax.inject.Inject

class PokemonDetailsPresenter(private val pokemon: Pokemon?) : MvpPresenter<PokemonDetailsView>() {

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var repositoryDetailsImpl: IRepositoryDetails

    @Inject
    lateinit var uiScheduler: Scheduler

    private var disposable = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadData()
    }

    fun loadData() {
        pokemon?.let {
            repositoryDetailsImpl.getPokemonDetails(it).observeOn(uiScheduler)
                .doOnSubscribe { viewState.showLoading() }
                .subscribe({ details ->
                    transform(details)
                    viewState.init(details)
                }, { error ->
                    error.message?.let { it1 -> Log.e("@@@", it1) }
                    viewState.showError()
                })
        }?.disposeBy(disposable)
    }

    private fun transform(details: DetailsPokemon) {
        details.height = details.height * 10
        details.weight = details.weight / 10

        details.name = details.name.firstCharToUpperCase()

        details.types[FIRST_ELEMENT].type.name =
            details.types[FIRST_ELEMENT].type.name.firstCharToUpperCase()

        if (details.types.size > 1) {
            details.types[SECOND_ELEMENT].type.name =
                details.types[SECOND_ELEMENT].type.name.firstCharToUpperCase()
        }
    }

    fun onBackPressed(): Boolean {
        router.exit()
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }
}