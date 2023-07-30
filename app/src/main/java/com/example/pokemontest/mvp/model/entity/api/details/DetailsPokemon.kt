package com.example.pokemontest.mvp.model.entity.api.details

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetailsPokemon(
    @Expose val id: Int,
    @Expose var name: String,
    @Expose val sprites: Sprites,
    @Expose val types: List<Type>,
    @Expose var height: Int,
    @Expose var weight: Float
) : Parcelable