package com.example.pokemontest.ui.imageLoader

interface IImageLoader<T> {
    fun loadInto(url: String?, container: T)
}