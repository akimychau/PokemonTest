package com.example.pokemontest.mvp.model.entity

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

@Parcelize
data class Pokemon(
    @Expose val name: String,
    @Expose val url: String
) : Parcelable
