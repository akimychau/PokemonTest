package com.example.pokemontest.mvp.model.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pokemontest.mvp.model.entity.database.RoomDetailsPokemon
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface DetailsPokemonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(pokemon: RoomDetailsPokemon): Completable

    @Delete
    fun delete(pokemon: RoomDetailsPokemon): Completable

    @Query("SELECT * FROM detailsPokemon WHERE listName = :name")
    fun findForPokemon(name: String): Single<RoomDetailsPokemon>
}