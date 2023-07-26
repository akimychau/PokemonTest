package com.example.pokemontest.di.list.module

import com.example.pokemontest.di.list.ListScope
import com.example.pokemontest.mvp.model.IRepository
import com.example.pokemontest.mvp.model.RepositoryImpl
import dagger.Module
import dagger.Provides

@Module
class ListModule{

    @ListScope
    @Provides
    fun getList(): IRepository = RepositoryImpl()
}