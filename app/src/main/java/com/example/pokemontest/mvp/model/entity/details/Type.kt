package com.example.pokemontest.mvp.model.entity.details

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

@Parcelize
data class Type(
    @Expose val slot: Int,
    @Expose val type: TypeX
) : Parcelable

@Parcelize
data class TypeX(
    @Expose val name: String
) : Parcelable