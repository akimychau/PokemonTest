package com.example.pokemontest.mvp.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.pokemontest.mvp.model.database.converter.ListConverter
import com.example.pokemontest.mvp.model.database.dao.DetailsPokemonDao
import com.example.pokemontest.mvp.model.database.dao.ListPokemonDao
import com.example.pokemontest.mvp.model.entity.database.RoomDetailsPokemon
import com.example.pokemontest.mvp.model.entity.database.RoomListPokemon

@Database(
    entities = [RoomListPokemon::class, RoomDetailsPokemon::class],
    version = 1
)
@TypeConverters(ListConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun listDao(): ListPokemonDao
    abstract fun detailsDao(): DetailsPokemonDao

    companion object {
        const val DB_NAME = "pokemon.db"
    }
}