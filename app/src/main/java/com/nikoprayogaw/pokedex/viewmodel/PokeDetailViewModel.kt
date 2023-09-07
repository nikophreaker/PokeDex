package com.nikoprayogaw.pokedex.viewmodel

import android.app.Application
import androidx.databinding.Observable
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableField
import androidx.databinding.ObservableList
import androidx.lifecycle.AndroidViewModel
import com.nikoprayogaw.pokedex.model.data.pokedetail.PokeDetail
import com.nikoprayogaw.pokedex.model.data.pokelist.*
import com.nikoprayogaw.pokedex.model.repo.DataRepo
import com.nikoprayogaw.pokedex.model.repo.DataSource
import com.nikoprayogaw.pokedex.utils.ConstantVariable
import com.nikoprayogaw.pokedex.utils.SingleLiveEvent
import com.nikoprayogaw.pokedex.view.LoadDataListener

class PokeDetailViewModel(application: Application, private val dataRepo: DataRepo) :
    AndroidViewModel(application) {
    val pokeDataDetail: ObservableField<PokeDetail> = ObservableField()
    var onLoadDataListener: LoadDataListener? = null

    fun start(id: String) {
        getPokeDetail(id)
    }

    private fun getPokeDetail(id: String) {
        dataRepo.getPokemonDetail(object : DataSource.GetPokemonDetailCallback {
            override fun onDataLoaded(pokeDetail: PokeDetail?) {
                pokeDataDetail.set(pokeDetail)
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