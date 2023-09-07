package com.nikoprayogaw.pokedex.utils

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nikoprayogaw.pokedex.model.data.pokelist.*
import com.nikoprayogaw.pokedex.view.adapter.PokeListAdapater

object RecyclerViewBinding {
    @BindingAdapter("setPokeList")
    @JvmStatic
    fun setPokeList(
        recyclerView: RecyclerView,
        pokeData: MutableList<Result?>
    ) {
        if (pokeData.isNotEmpty()) {
            with(recyclerView.adapter as? PokeListAdapater) {
                this?.replaceData(pokeData)
            }
        }
    }
}