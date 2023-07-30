package com.example.pokemontest.mvp.model

import com.example.pokemontest.mvp.model.entity.api.details.DetailsPokemon
import com.example.pokemontest.mvp.model.entity.api.list.Pokemon
import io.reactivex.rxjava3.core.Single

interface IRepositoryDetails {

    fun getPokemonDetails(pokemon: Pokemon): Single<DetailsPokemon>
}