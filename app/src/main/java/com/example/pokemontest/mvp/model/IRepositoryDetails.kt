package com.example.pokemontest.mvp.model

import com.example.pokemontest.mvp.model.entity.details.DetailsPokemon
import io.reactivex.rxjava3.core.Single

interface IRepositoryDetails {

    fun getPokemonDetails(uri: String): Single<DetailsPokemon>
}