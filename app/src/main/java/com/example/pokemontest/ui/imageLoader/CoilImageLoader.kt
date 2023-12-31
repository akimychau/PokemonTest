package com.example.pokemontest.ui.imageLoader

import android.widget.ImageView
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.example.pokemontest.R

class CoilImageLoader : IImageLoader<ImageView> {

    override fun loadInto(url: String?, container: ImageView) {
        val imageLoader = ImageLoader.Builder(container.context)
            .componentRegistry { add(SvgDecoder(container.context)) }
            .build()

        val request = ImageRequest.Builder(container.context)
            .crossfade(true)
            .crossfade(500)
            .placeholder(R.drawable.pokeball)
            .error(R.drawable.baseline_report_gmailerrorred_24)
            .data(url)
            .fallback(R.drawable.baseline_question_mark_24)
            .target(container)
            .build()

        imageLoader.enqueue(request)
    }
}