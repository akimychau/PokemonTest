package com.example.pokemontest.di

import com.example.pokemontest.di.list.ListSubComponent
import com.example.pokemontest.di.modules.ApiModule
import com.example.pokemontest.di.modules.AppModule
import com.example.pokemontest.di.modules.CiceroneModule
import com.example.pokemontest.di.modules.ImageLoaderModule
import com.example.pokemontest.mvp.presenter.MainPresenter
import com.example.pokemontest.mvp.view.activity.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApiModule::class,
        AppModule::class,
        CiceroneModule::class,
        ImageLoaderModule::class
    ]
)
interface AppComponent {

    fun listSubComponent(): ListSubComponent

    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
}