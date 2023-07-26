package com.example.pokemontest.mvp.model

class RepositoryImpl : IRepository {

    private val testList = listOf(
        Pokemon("Pikachu1"),
        Pokemon("Pikachu2"),
        Pokemon("Pikachu3"),
        Pokemon("Pikachu4"),
        Pokemon("Pikachu5"),
        Pokemon("Pikachu6"),
        Pokemon("Pikachu7"),
        Pokemon("Pikachu8"),
        Pokemon("Pikachu9")
    )

    override fun getList() = testList
}