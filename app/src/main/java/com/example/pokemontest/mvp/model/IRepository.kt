package com.example.pokemontest.mvp.model

interface IRepository {

    fun getList(): List<Pokemon>
}