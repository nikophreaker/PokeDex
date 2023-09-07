package com.nikoprayogaw.pokedex.viewmodel

import android.app.Application
import androidx.databinding.Observable
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableField
import androidx.databinding.ObservableList
import androidx.lifecycle.AndroidViewModel
import com.nikoprayogaw.pokedex.model.data.pokedetail.PokeDetail
import com.nikoprayogaw.pokedex.model.data.pokedetail.Type
import com.nikoprayogaw.pokedex.model.data.pokelist.*
import com.nikoprayogaw.pokedex.model.repo.DataRepo
import com.nikoprayogaw.pokedex.model.repo.DataSource
import com.nikoprayogaw.pokedex.utils.ConstantVariable
import com.nikoprayogaw.pokedex.utils.SingleLiveEvent
import com.nikoprayogaw.pokedex.view.LoadDataListener

class PokeDetailViewModel(application: Application, private val dataRepo: DataRepo) :
    AndroidViewModel(application) {
    val pokeDataDetail: ObservableField<PokeDetail> = ObservableField()
    val pokeTypeList: ObservableList<String> = ObservableArrayList()
    val rate = SingleLiveEvent<Int>()
    val type1 = SingleLiveEvent<String>()
    val type2 = SingleLiveEvent<String>()
    val type3 = SingleLiveEvent<String>()
    val move1 = SingleLiveEvent<String>()
    val move2 = SingleLiveEvent<String>()
    val move3 = SingleLiveEvent<String>()
    val move4 = SingleLiveEvent<String>()
    val allType = SingleLiveEvent<MutableList<String>>()
    var onLoadDataListener: LoadDataListener? = null

    fun start(id: String) {
        getPokeDetail(id)
    }

    private fun getPokeDetail(id: String) {
        dataRepo.getPokemonDetail(object : DataSource.GetPokemonDetailCallback {
            override fun onDataLoaded(pokeDetail: PokeDetail?) {
                pokeDataDetail.set(pokeDetail)
                if (pokeDetail?.types?.isNotEmpty() == true) {
                    pokeDetail.types.getOrNull(0).let {
                        type3.value = it?.type?.name
                        pokeTypeList.add(it?.type?.name.toString())
                    }
                    pokeDetail.types.getOrNull(1).let {
                        type2.value = it?.type?.name
                        pokeTypeList.add(it?.type?.name.toString())
                    }
                    pokeDetail.types.getOrNull(2).let {
                        type1.value = it?.type?.name
                        pokeTypeList.add(it?.type?.name.toString())
                    }
                }
                if (pokeDetail?.moves?.isNotEmpty() == true) {
                    pokeDetail.moves.getOrNull(0).let {
                        move1.value = it?.move?.name
                    }
                    pokeDetail.moves.getOrNull(1).let {
                        move2.value = it?.move?.name
                    }
                    pokeDetail.moves.getOrNull(2).let {
                        move3.value = it?.move?.name
                    }
                    pokeDetail.moves.getOrNull(3).let {
                        move4.value = it?.move?.name
                    }
                }
                val dataPokemon = pokeDataDetail.get()?.stats
                rate.value = ((dataPokemon?.get(0)?.baseStat ?: 0) + (dataPokemon?.get(1)?.baseStat ?: 0) + (dataPokemon?.get(2)?.baseStat ?: 0) +
                        (dataPokemon?.get(3)?.baseStat ?: 0) + (dataPokemon?.get(4)?.baseStat ?: 0) + (dataPokemon?.get(5)?.baseStat ?: 0)) / 6
                onLoadDataListener?.onDataLoad()
            }

            override fun onNotAvailable() {
                onLoadDataListener?.onNotAvailable()
            }

            override fun onError(msg: String?) {
                onLoadDataListener?.onError(msg)
            }

        }, id)
    }
}