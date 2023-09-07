package com.nikoprayogaw.pokedex.model.repo

import com.nikoprayogaw.pokedex.model.data.pokedetail.PokeDetail
import com.nikoprayogaw.pokedex.model.data.pokelist.PokeList
import com.nikoprayogaw.pokedex.model.data.pokelist.Result

interface DataSource {

    fun getPokemonList(callback: GetPokemonListCallback, offset: Int, limit: Int)
    fun getPokemonDetail(callback: GetPokemonDetailCallback, id: String)

    interface GetPokemonListCallback {
        fun onDataLoaded(pokeList: MutableList<Result?>)
        fun onNotAvailable()
        fun onError(msg: String?)
    }

    interface GetPokemonDetailCallback {
        fun onDataLoaded(pokeDetail: PokeDetail?)
        fun onNotAvailable()
        fun onError(msg: String?)
    }
}