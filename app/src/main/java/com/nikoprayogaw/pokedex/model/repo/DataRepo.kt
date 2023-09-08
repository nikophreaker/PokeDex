package com.nikoprayogaw.pokedex.model.repo

import android.util.Log
import com.nikoprayogaw.pokedex.model.data.pokedetail.PokeDetail
import com.nikoprayogaw.pokedex.model.data.pokelist.PokeList
import com.nikoprayogaw.pokedex.model.data.pokelist.Result
import com.nikoprayogaw.pokedex.model.repo.source.local.DataLocalSource
import com.nikoprayogaw.pokedex.model.repo.source.remote.DataRemoteSource

class DataRepo constructor(
    private val remoteSource: DataRemoteSource,
    private val localSource: DataLocalSource
): DataSource {
    override fun getPokemonList(
        callback: DataSource.GetPokemonListCallback,
        offset: Int,
        limit: Int
    ) {
        remoteSource.getPokemonList(object : DataSource.GetPokemonListCallback {
            override fun onDataLoaded(pokeList: MutableList<Result?>) {
                callback.onDataLoaded(pokeList)
            }

            override fun onNotAvailable() {
                callback.onNotAvailable()
            }

            override fun onError(msg: String?) {
                callback.onError(msg)
                localSource.getPokemonList(object : DataSource.GetPokemonListCallback {
                    override fun onDataLoaded(pokeList: MutableList<Result?>) {
                        callback.onDataLoaded(pokeList)
                        Log.i("pokemonlist", pokeList.toString())
                    }

                    override fun onNotAvailable() {

                    }

                    override fun onError(msg: String?) {

                    }

                }, offset, limit)
            }

        } , offset, limit)
    }

    override fun getPokemonDetail(callback: DataSource.GetPokemonDetailCallback, id: String) {
        remoteSource.getPokemonDetail(object : DataSource.GetPokemonDetailCallback {
            override fun onDataLoaded(pokeDetail: PokeDetail?) {
                callback.onDataLoaded(pokeDetail)
            }

            override fun onNotAvailable() {
                callback.onNotAvailable()
            }

            override fun onError(msg: String?) {
                callback.onError(msg)
            }

        } , id)

        localSource.getPokemonDetail(object : DataSource.GetPokemonDetailCallback {
            override fun onDataLoaded(pokeDetail: PokeDetail?) {
                callback.onDataLoaded(pokeDetail)
            }

            override fun onNotAvailable() {

            }

            override fun onError(msg: String?) {

            }

        }, id)
    }

    companion object {
        private var INSTANCE: DataRepo? = null

        @JvmStatic
        fun getInstance(
            mainDataRemoteSource: DataRemoteSource,
            newsLocalDataSource: DataLocalSource
        ) =
            INSTANCE ?: synchronized(DataRepo::class.java) {
                INSTANCE ?: DataRepo(mainDataRemoteSource, newsLocalDataSource)
                    .also { INSTANCE = it }

            }

        @JvmStatic
        fun destroyInstance() {
            INSTANCE = null
        }
    }
}