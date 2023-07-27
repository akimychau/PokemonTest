package com.example.pokemontest.mvp.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pokemontest.App
import com.example.pokemontest.databinding.FragmentPokemonDetailsBinding
import com.example.pokemontest.mvp.model.entity.Pokemon
import com.example.pokemontest.mvp.presenter.PokemonDetailsPresenter
import com.example.pokemontest.mvp.view.PokemonDetailsView
import com.example.pokemontest.navigation.BackPressedListener
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class PokemonDetailsFragment : MvpAppCompatFragment(), PokemonDetailsView, BackPressedListener {

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

        _viewBinding = FragmentPokemonDetailsBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun showName(name: String) {
        viewBinding.testText.text = name
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