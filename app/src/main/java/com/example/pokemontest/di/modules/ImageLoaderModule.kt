package com.example.pokemontest.di.modules

import android.widget.ImageView
import com.example.pokemontest.ui.imageLoader.CoilImageLoader
import com.example.pokemontest.ui.imageLoader.IImageLoader
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ImageLoaderModule {

    @Singleton
    @Provides
    fun loadInto(): IImageLoader<ImageView> = CoilImageLoader()
}