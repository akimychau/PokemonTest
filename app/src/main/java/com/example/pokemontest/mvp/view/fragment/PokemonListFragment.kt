package com.example.pokemontest.mvp.view.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokemontest.App
import com.example.pokemontest.databinding.FragmentPokemonListBinding
import com.example.pokemontest.mvp.presenter.PokemonListPresenter
import com.example.pokemontest.mvp.view.PokemonListView
import com.example.pokemontest.mvp.view.adapter.PokemonRVAdapter
import com.example.pokemontest.navigation.BackPressedListener
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class PokemonListFragment : MvpAppCompatFragment(), PokemonListView, BackPressedListener {

    private var _viewBinding: FragmentPokemonListBinding? = null
    private val viewBinding get() = _viewBinding!!

    private var adapter: PokemonRVAdapter? = null

    private val presenter by moxyPresenter {
        PokemonListPresenter().apply {
            App.instance.initListSubComponent().inject(this)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)

        _viewBinding = FragmentPokemonListBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun init() {
        viewBinding.rvPokemonList.layoutManager = LinearLayoutManager(context)
        adapter = PokemonRVAdapter(presenter.pokemonItemPresenter).apply {
            App.instance.initListSubComponent().inject(this)
        }
        viewBinding.rvPokemonList.adapter = adapter
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun updateList() {
        adapter?.notifyDataSetChanged()
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
        fun instance() = PokemonListFragment()
    }
}