package com.nikoprayogaw.pokedex.utils

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nikoprayogaw.pokedex.model.data.pokedetail.Type
import com.nikoprayogaw.pokedex.model.data.pokelist.*
import com.nikoprayogaw.pokedex.view.adapter.*

object RecyclerViewBinding {
    @BindingAdapter("setPokeList")
    @JvmStatic
    fun setPokeList(
        recyclerView: RecyclerView,
        pokeData: MutableList<Result?>
    ) {
        if (pokeData.isNotEmpty()) {
            with(recyclerView.adapter as? PokeListAdapter) {
                this?.replaceData(pokeData)
            }
        }
    }

    @BindingAdapter("setPokeType")
    @JvmStatic
    fun setPokeType(
        recyclerView: RecyclerView,
        pokeType: MutableList<Type?>
    ) {
        if (pokeType.isNotEmpty()) {
            with(recyclerView.adapter as? PokeTypeAdapter) {
                this?.replaceData(pokeType)
            }
        }
    }
}