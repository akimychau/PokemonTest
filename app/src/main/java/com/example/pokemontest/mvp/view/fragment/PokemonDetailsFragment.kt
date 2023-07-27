package com.example.pokemontest.mvp.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.pokemontest.App
import com.example.pokemontest.databinding.FragmentPokemonDetailsBinding
import com.example.pokemontest.mvp.model.entity.details.DetailsPokemon
import com.example.pokemontest.mvp.model.entity.list.Pokemon
import com.example.pokemontest.mvp.presenter.PokemonDetailsPresenter
import com.example.pokemontest.mvp.view.PokemonDetailsView
import com.example.pokemontest.mvp.view.imageView.IImageLoader
import com.example.pokemontest.navigation.BackPressedListener
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class PokemonDetailsFragment : MvpAppCompatFragment(), PokemonDetailsView, BackPressedListener {

    @Inject
    lateinit var imageLoader: IImageLoader<ImageView>

    private var _viewBinding: FragmentPokemonDetailsBinding? = null
    private val viewBinding get() = _viewBinding!!

    private val presenter by moxyPresenter {
        val pokemon = arguments?.getParcelable(BUNDLE_POKEMON) as Pokemon?
        PokemonDetailsPresenter(pokemon).apply {
            App.instance.initDetailsSubComponent()?.inject(this)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)

        App.instance.initDetailsSubComponent()?.inject(this)

        _viewBinding = FragmentPokemonDetailsBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun init(pokemon: DetailsPokemon) {
        viewBinding.testText.text = pokemon.name
        viewBinding.height.text = pokemon.height.toString()
        viewBinding.weight.text = pokemon.weight.toString()
        imageLoader.loadInto(pokemon.sprites.other.dream_world.front_default, viewBinding.image)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _viewBinding = null
    }

    override fun onBackPressed(): Boolean {
        presenter.onBackPressed()
        return true
    }

    companion object {
        const val BUNDLE_POKEMON = "BUNDLE_POKEMON"
        fun instance(pokemon: Pokemon) = PokemonDetailsFragment().apply {
            arguments = Bundle().apply {
                putParcelable(BUNDLE_POKEMON, pokemon)
            }
        }
    }
}