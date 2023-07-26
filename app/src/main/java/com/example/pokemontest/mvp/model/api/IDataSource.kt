package com.example.pokemontest.mvp.model.api

import com.example.pokemontest.mvp.model.entity.ListPokemon
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface IDataSource {

    @GET("/api/v2/pokemon")
    fun getAllPokemon(): Single<ListPokemon>
}