package com.example.pokemontest.di.details

interface DetailsScopeContainer {

    fun initDetailsSubComponent(): DetailsSubComponent?

    fun releaseDetailsSubcomponent()
}