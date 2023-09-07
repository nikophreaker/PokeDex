package com.nikoprayogaw.pokedex.viewmodel

import android.app.Application
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import androidx.lifecycle.AndroidViewModel
import com.nikoprayogaw.pokedex.model.data.pokelist.*
import com.nikoprayogaw.pokedex.model.repo.DataRepo
import com.nikoprayogaw.pokedex.model.repo.DataSource
import com.nikoprayogaw.pokedex.utils.ConstantVariable
import com.nikoprayogaw.pokedex.utils.SingleLiveEvent
import com.nikoprayogaw.pokedex.view.LoadDataListener

class PokeListViewModel(application: Application, private val dataRepo: DataRepo) : AndroidViewModel(application) {
    val pokeDataList: ObservableList<Result> = ObservableArrayList()
    internal val openPokemon = SingleLiveEvent<String>()
    var isLoading = SingleLiveEvent<Boolean>()
    var onLoadDataListener: LoadDataListener? = null

    fun start() {
        getPokeList(ConstantVariable.offset,ConstantVariable.limit)
    }

    fun resume(offset: Int, limit: Int) {
        isLoading.value = true
        if (isLoading.value == true) {
            getPokeList(offset, limit)
        }
    }

    private fun getPokeList(offset: Int, limit: Int) {
        dataRepo.getPokemonList(object : DataSource.GetPokemonListCallback{
            override fun onDataLoaded(pokeList: MutableList<Result?>) {
                if (offset == 0) {
                    with(pokeDataList) {
                        clear()
                        addAll(pokeList)
                    }
                    onLoadDataListener?.onDataLoad()
                } else {
                    with(pokeDataList) {
                        addAll(pokeList)
                    }
                    onLoadDataListener?.onDataResumeLoad()
                    isLoading.value = false
                }
            }

            override fun onNotAvailable() {
                if (offset == 0) {
                    pokeDataList.clear()
                    onLoadDataListener?.onNotAvailable()
                } else {
                    onLoadDataListener?.onNotAvailableResume()
                    isLoading.value = false
                }
            }

            override fun onError(msg: String?) {
                if (offset == 0) {
                    onLoadDataListener?.onError(msg)
                } else {
                    onLoadDataListener?.onErrorResume(msg)
                    isLoading.value = false
                }
            }

        }, offset, limit)
    }
}