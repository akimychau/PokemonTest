package com.example.pokemontest.mvp.model.api

import com.example.pokemontest.mvp.model.entity.ListPokemon
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface IDataSource {

    @GET("/api/v2/pokemon")
    fun getAllPokemon(@Query("limit") limit: String?): Single<ListPokemon>

    @GET
    fun previousPage(@Url previous: String?, @Query("limit") limit: String?): Single<ListPokemon>

    @GET
    fun nextPage(@Url next: String?, @Query("limit") limit: String?): Single<ListPokemon>
}