package com.example.pokemontest.mvp.model.entity.api.list

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

@Parcelize
data class ListPokemon(
    @Expose val count: Int?,
    @Expose val next: String?,
    @Expose val previous: String?,
    @Expose val results: List<Pokemon>
) : Parcelable