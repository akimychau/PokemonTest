package com.example.pokemontest.di.modules

import com.example.pokemontest.App
import dagger.Module
import dagger.Provides

@Module
class AppModule(private val app: App) {

    @Provides
    fun app(): App {
        return app
    }
}