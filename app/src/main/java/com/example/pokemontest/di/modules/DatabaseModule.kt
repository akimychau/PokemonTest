package com.example.pokemontest.di.modules


import androidx.room.Room
import com.example.pokemontest.App
import com.example.pokemontest.mvp.model.database.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun database(app: App) = Room.databaseBuilder(
        app, AppDatabase::class.java,
        AppDatabase.DB_NAME
    ).build()
}