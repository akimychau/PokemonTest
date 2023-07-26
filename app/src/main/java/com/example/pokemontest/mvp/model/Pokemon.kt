package com.example.pokemontest.mvp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Pokemon(val name: String) : Parcelable
