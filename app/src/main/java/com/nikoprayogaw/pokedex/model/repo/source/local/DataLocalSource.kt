package com.nikoprayogaw.pokedex.model.repo.source.local

import android.content.SharedPreferences
import androidx.annotation.VisibleForTesting
import com.nikoprayogaw.pokedex.model.repo.DataSource

class DataLocalSource private constructor(
    private val preferences: SharedPreferences,
    private val localDB: PokedexDatabase
): DataSource {
    override fun getPokemonList(
        callback: DataSource.GetPokemonListCallback,
        offset: Int,
        limit: Int
    ) {
        if (localDB.pokeListDao().getPokeList() != null) {
            callback.onDataLoaded(localDB.pokeListDao().getPokeList()!!)
        } else {
            callback.onNotAvailable()
        }
    }

    override fun getPokemonDetail(callback: DataSource.GetPokemonDetailCallback, id: String) {

    }


    companion object {
        private var INSTANCE: DataLocalSource? = null

        @JvmStatic
        fun getInstance(preferences: SharedPreferences,
                        localDB: PokedexDatabase): DataLocalSource? {
            if (INSTANCE == null) {
                synchronized(DataLocalSource::class.java) {
                    INSTANCE = DataLocalSource(preferences,localDB)
                }
            }
            return INSTANCE
        }

        @VisibleForTesting
        fun clearInstance() {
            INSTANCE = null
        }
    }

}