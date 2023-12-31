package com.example.pokemontest.mvp.model.api

import com.example.pokemontest.mvp.model.entity.api.details.DetailsPokemon
import com.example.pokemontest.mvp.model.entity.api.list.ListPokemon
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface IDataSource {

    @GET("/api/v2/pokemon")
    fun getAllPokemon(@Query("limit") limit: String?): Single<ListPokemon>

    @GET
    fun previousPage(@Url previous: String?): Single<ListPokemon>
    @GET
    fun nextPage(@Url next: String?): Single<ListPokemon>

    @GET
    fun getPokemonDetails(@Url url: String): Single<DetailsPokemon>
}