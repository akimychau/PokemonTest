package com.example.pokemontest.mvp.model.entity.api.details

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

@Parcelize
data class Sprites(
    @Expose val other: DreamWorld
) : Parcelable

@Parcelize
data class DreamWorld(
    @Expose val dream_world: Front
) : Parcelable

@Parcelize
data class Front(
    @Expose val front_default: String?
) : Parcelable