package com.example.pokemontest.mvp.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import com.example.pokemontest.App
import com.example.pokemontest.databinding.FragmentPokemonDetailsBinding
import com.example.pokemontest.mvp.model.entity.api.details.DetailsPokemon
import com.example.pokemontest.mvp.model.entity.api.list.Pokemon
import com.example.pokemontest.mvp.presenter.PokemonDetailsPresenter
import com.example.pokemontest.mvp.view.PokemonDetailsView
import com.example.pokemontest.mvp.view.activity.MainActivity
import com.example.pokemontest.navigation.BackPressedListener
import com.example.pokemontest.ui.imageLoader.IImageLoader
import com.example.pokemontest.utils.FIRST_ELEMENT
import com.example.pokemontest.utils.SECOND_ELEMENT
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class PokemonDetailsFragment : MvpAppCompatFragment(), PokemonDetailsView, BackPressedListener,
    MenuProvider {

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.btnRefresh.setOnClickListener {
            presenter.loadData()
            viewBinding.textError.visibility = View.GONE
            Toast.makeText(
                requireContext(),
                "Trying to download data",
                Toast.LENGTH_SHORT
            )
                .show()
        }

        addMenuProvider(true)
    }


    override fun init(pokemon: DetailsPokemon) {
        viewBinding.name.text = pokemon.name

        "Height: ${pokemon.height} cm".also { viewBinding.height.text = it }
        "Weight: ${pokemon.weight} kg".also { viewBinding.weight.text = it }

        imageLoader.loadInto(pokemon.sprites.other.dream_world.front_default, viewBinding.image)

        viewBinding.typeOne.text = pokemon.types[FIRST_ELEMENT].type.name
        if (pokemon.types.size > 1) {
            viewBinding.typeTwo.visibility = View.VISIBLE
            viewBinding.typeTwo.text = pokemon.types[SECOND_ELEMENT].type.name
        }
        viewBinding.progressBar.visibility = View.GONE
        viewBinding.btnRefresh.visibility = View.GONE
        viewBinding.textError.visibility = View.GONE
    }

    override fun showLoading() {
        viewBinding.progressBar.visibility = View.VISIBLE
    }

    override fun showError() {
        viewBinding.textError.visibility = View.VISIBLE
        viewBinding.btnRefresh.visibility = View.VISIBLE
        viewBinding.progressBar.visibility = View.GONE
    }

    override fun onDestroyView() {
        addMenuProvider(false)
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

    private fun addMenuProvider(isEnable: Boolean) {
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)
        (requireActivity() as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(isEnable)
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            android.R.id.home -> {
                presenter.onBackPressed()
            }
        }
        return false
    }
}