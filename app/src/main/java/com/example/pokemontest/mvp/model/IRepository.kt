package com.example.pokemontest.mvp.model

import com.example.pokemontest.mvp.model.entity.ListPokemon
import io.reactivex.rxjava3.core.Single

interface IRepository {

    fun getList(): Single<ListPokemon>

    fun nextPage(next: String?): Single<ListPokemon>
    fun previousPage(previous: String?): Single<ListPokemon>
}