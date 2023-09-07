package com.nikoprayogaw.pokedex.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.nikoprayogaw.pokedex.R
import com.nikoprayogaw.pokedex.databinding.ItemPokemonBinding
import com.nikoprayogaw.pokedex.model.data.pokelist.*
import com.nikoprayogaw.pokedex.view.PokeClickListener
import com.nikoprayogaw.pokedex.viewmodel.PokeListViewModel

class PokeListAdapater(
    private var pokeDataList: MutableList<Result?>,
    private var pokeListViewModel: PokeListViewModel
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val pokeItemBinding: ItemPokemonBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.item_pokemon,
            parent,false
        )

        return PokeHolder(pokeItemBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = pokeDataList[position]
        val actionListener = object : PokeClickListener {
            override fun onPokemonClick() {
                pokeListViewModel.openPokemon.value = data?.url?.substring(data.url.lastIndexOf("n/")+2, data.url.lastIndexOf("/"))
            }

        }
        (holder as PokeHolder).bindRows(data, actionListener)
    }

    override fun getItemCount(): Int = pokeDataList.size

    fun replaceData(pokeData: MutableList<Result?>) {
        setList(pokeData)
    }

    fun setList(pokeData: MutableList<Result?>) {
        this.pokeDataList = pokeData
        notifyDataSetChanged()
    }

    class PokeHolder(binding: ItemPokemonBinding) : RecyclerView.ViewHolder(binding.root) {
        val itemItemBinding = binding

        fun bindRows(pokeData: Result?, userActionListener: PokeClickListener) {
            itemItemBinding.data = pokeData
            itemItemBinding.action = userActionListener
            itemItemBinding.executePendingBindings()
        }
    }
}