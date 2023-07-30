package com.example.pokemontest.mvp.model.entity.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "listPokemon")
data class RoomListPokemon(
    @PrimaryKey
    val name: String,
    val url: String
)