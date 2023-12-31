package com.example.pokemontest.mvp.model.entity.api.list

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

@Parcelize
data class Pokemon(
    @Expose val name: String,
    @Expose val url: String
) : Parcelable
