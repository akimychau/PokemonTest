package com.example.pokemontest.mvp.model.entity.database

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.pokemontest.mvp.model.entity.api.details.Type

@Entity(
    tableName = "detailsPokemon",
    foreignKeys = [ForeignKey(
        entity = RoomListPokemon::class,
        parentColumns = ["name"],
        childColumns = ["listName"],
        onDelete = ForeignKey.NO_ACTION
    )]
)
data class RoomDetailsPokemon(
    @PrimaryKey
    val id: Int,
    val name: String,
    val image: String?,
    val types: List<Type>,
    val height: Int,
    val weight: Float,
    val listName: String
)