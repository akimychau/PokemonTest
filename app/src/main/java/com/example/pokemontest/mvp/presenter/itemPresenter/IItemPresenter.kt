package com.example.pokemontest.mvp.presenter.itemPresenter

import com.example.pokemontest.mvp.view.itemView.IItemView

interface IItemPresenter<V : IItemView> {

    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}