package com.example.pokemontest.mvp.model.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pokemontest.mvp.model.entity.database.RoomListPokemon
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface ListPokemonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(pokemon: List<RoomListPokemon>): Completable

    @Delete
    fun delete(pokemon: RoomListPokemon): Completable

    @Query("SELECT * FROM listPokemon")
    fun getAllPokemon(): Single<List<RoomListPokemon>>
}