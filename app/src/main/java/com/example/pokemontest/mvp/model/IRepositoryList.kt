package com.example.pokemontest.mvp.model

import com.example.pokemontest.mvp.model.entity.api.list.ListPokemon
import io.reactivex.rxjava3.core.Single

interface IRepositoryList {

    fun getList(limit: String?): Single<ListPokemon>

    fun nextPage(next: String?): Single<ListPokemon>
    fun previousPage(previous: String?): Single<ListPokemon>
}