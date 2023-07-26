package com.example.pokemontest

import android.app.Application
import com.example.pokemontest.di.AppComponent
import com.example.pokemontest.di.DaggerAppComponent
import com.example.pokemontest.di.list.ListScopeContainer
import com.example.pokemontest.di.list.ListSubComponent
import com.example.pokemontest.di.modules.AppModule

class App : Application(), ListScopeContainer {

    lateinit var appComponent: AppComponent

    private var listSubComponent: ListSubComponent? = null

    override fun onCreate() {
        super.onCreate()
        instance = this

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

    companion object {
        lateinit var instance: App
    }

    override fun initListSubComponent() = appComponent.listSubComponent().also {
        listSubComponent = it
    }

    override fun releaseListSubcomponent() {
        listSubComponent = null
    }
}