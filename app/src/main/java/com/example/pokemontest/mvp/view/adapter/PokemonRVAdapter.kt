package com.example.pokemontest.mvp.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemontest.databinding.ItemPokemonNameBinding
import com.example.pokemontest.mvp.presenter.itemPresenter.IPokemonItemPresenter
import com.example.pokemontest.mvp.view.itemView.IPokemonItemView
import com.example.pokemontest.utils.RV_INVALID_INDEX

class PokemonRVAdapter(private val presenter: IPokemonItemPresenter) :
    RecyclerView.Adapter<PokemonRVAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            ItemPokemonNameBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        ).apply {
            itemView.setOnClickListener {
                presenter.itemClickListener?.invoke(this)
            }
        }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        presenter.bindView(holder.apply { pos = position })

    override fun getItemCount() = presenter.getCount()

    inner class ViewHolder(private val vb: ItemPokemonNameBinding) :
        RecyclerView.ViewHolder(vb.root), IPokemonItemView {

        override fun setName(name: String) {
            vb.name.text = name
        }

        override var pos = RV_INVALID_INDEX
    }
}