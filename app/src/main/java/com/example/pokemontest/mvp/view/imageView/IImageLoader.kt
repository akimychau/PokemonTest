package com.example.pokemontest.mvp.view.imageView

interface IImageLoader<T> {
    fun loadInto(url: String, container: T)
}