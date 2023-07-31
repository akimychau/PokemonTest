package com.example.pokemontest.mvp.view.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokemontest.App
import com.example.pokemontest.R
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

    private val presenter: PokemonListPresenter by moxyPresenter {
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        clickListeners()
    }

    private fun clickListeners() {
        viewBinding.nextBtn.setOnClickListener { presenter.nextPage() }

        viewBinding.previousBtn.setOnClickListener { presenter.previousPage() }

        viewBinding.inputLayout.setEndIconOnClickListener {
            presenter.loadData(viewBinding.inputEditText.text.toString())
        }

        viewBinding.btnRefresh.setOnClickListener {
            presenter.loadData(null)
            Toast.makeText(
                requireContext(),
                "Trying to download data",
                Toast.LENGTH_SHORT
            )
                .show()}
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

    override fun showCount(count: Int) {
        viewBinding.inputLayout.helperText = getString(R.string.max_value_is) + count.toString()
    }

    override fun showEmptyToast() {
        Toast.makeText(
            requireContext(),
            "There is no more pages",
            Toast.LENGTH_SHORT
        )
            .show()
    }

    override fun showLoading() {
        viewBinding.progressBar.visibility = View.VISIBLE
    }

    override fun showError() {
        viewBinding.textError.visibility = View.VISIBLE
        viewBinding.inputLayout.visibility = View.GONE
        viewBinding.nextBtn.visibility = View.GONE
        viewBinding.previousBtn.visibility = View.GONE
        viewBinding.rvPokemonList.visibility = View.VISIBLE
        viewBinding.btnRefresh.visibility = View.VISIBLE
        viewBinding.progressBar.visibility = View.GONE
    }

    override fun showSuccess() {
        viewBinding.textError.visibility = View.GONE
        viewBinding.progressBar.visibility = View.GONE
        viewBinding.rvPokemonList.visibility = View.VISIBLE
        viewBinding.inputLayout.visibility = View.VISIBLE
        viewBinding.nextBtn.visibility = View.VISIBLE
        viewBinding.previousBtn.visibility = View.VISIBLE
        viewBinding.btnRefresh.visibility = View.GONE
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