package com.example.pokemontest.di.list

interface ListScopeContainer {

    fun initListSubComponent(): ListSubComponent

    fun releaseListSubcomponent()
}