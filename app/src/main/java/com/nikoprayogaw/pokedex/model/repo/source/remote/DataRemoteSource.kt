package com.nikoprayogaw.pokedex.model.repo.source.remote

import android.annotation.SuppressLint
import androidx.annotation.VisibleForTesting
import com.nikoprayogaw.pokedex.model.api.ApiService
import com.nikoprayogaw.pokedex.model.repo.DataSource
import com.nikoprayogaw.pokedex.model.repo.source.local.PokedexDatabase
import com.nikoprayogaw.pokedex.utils.ConstantVariable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DataRemoteSource(
    private val localDB: PokedexDatabase
) : DataSource {
    private val apiService = ApiService.create(ConstantVariable.url)

    @SuppressLint("CheckResult")
    override fun getPokemonList(
        callback: DataSource.GetPokemonListCallback,
        offset: Int,
        limit: Int
    ) {
        apiService.getPokeList(offset, limit)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                run {
                    if (it.results != null) {
                        callback.onDataLoaded(it.results.toMutableList())
                        localDB.pokeListDao().deletePokelist()
                        localDB.pokeListDao().addData(it.results.toMutableList())
                    } else {
                        callback.onNotAvailable()
                    }
                }
            }, {
                callback.onError(it.message)
            })
    }

    @SuppressLint("CheckResult")
    override fun getPokemonDetail(callback: DataSource.GetPokemonDetailCallback, id: String) {
        apiService.getPokeDetail(id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                run {
                    if (it != null) {
                        callback.onDataLoaded(it)
                    } else {
                        callback.onNotAvailable()
                    }
                }
            }, {
                callback.onError(it.message)
            })

    }

    companion object {
        private var INSTANCE: DataRemoteSource? = null

        @JvmStatic
        fun getInstance(localDB: PokedexDatabase): DataRemoteSource? {
            if (INSTANCE == null) {
                synchronized(DataRemoteSource::class.java) {
                    INSTANCE = DataRemoteSource(localDB)
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