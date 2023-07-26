package com.example.pokemontest.mvp.model

import com.example.pokemontest.mvp.model.entity.ListPokemon
import io.reactivex.rxjava3.core.Single

interface IRepository {

    fun getList(): Single<ListPokemon>
}