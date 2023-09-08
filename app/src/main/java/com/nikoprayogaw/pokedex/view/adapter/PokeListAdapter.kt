package com.nikoprayogaw.pokedex.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.nikoprayogaw.pokedex.R
import com.nikoprayogaw.pokedex.databinding.ItemPokemonBinding
import com.nikoprayogaw.pokedex.model.data.pokelist.*
import com.nikoprayogaw.pokedex.view.PokeClickListener
import com.nikoprayogaw.pokedex.viewmodel.PokeListViewModel

class PokeListAdapter(
    private var pokeDataList: MutableList<Result?>,
    private var pokeListViewModel: PokeListViewModel
): RecyclerView.Adapter<RecyclerView.ViewHolder>(), Filterable {
    var pokeDataListFiltered: MutableList<Result?> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val pokeItemBinding: ItemPokemonBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.item_pokemon,
            parent,false
        )

        return PokeHolder(pokeItemBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = pokeDataListFiltered[position]
        val actionListener = object : PokeClickListener {
            override fun onPokemonClick() {
                pokeListViewModel.openPokemon.value = data?.url?.substring(data.url.lastIndexOf("n/")+2, data.url.lastIndexOf("/"))
            }

        }
        val typeColor = listOf("grass", "fire","water","electric","poison","ground")
        (holder as PokeHolder).bindRows(data, typeColor, actionListener)
    }

    override fun getItemCount(): Int = pokeDataListFiltered.size

    fun replaceData(pokeData: MutableList<Result?>) {
        setList(pokeData)
    }

    private fun setList(pokeData: MutableList<Result?>) {
        this.pokeDataList = pokeData
        notifyDataSetChanged()
    }

    class PokeHolder(binding: ItemPokemonBinding) : RecyclerView.ViewHolder(binding.root) {
        private val itemItemBinding = binding

        fun bindRows(pokeData: Result?, typeColor: List<String>, userActionListener: PokeClickListener) {
            itemItemBinding.data = pokeData
            itemItemBinding.typeColor = typeColor
            itemItemBinding.action = userActionListener
            itemItemBinding.executePendingBindings()
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charString = constraint?.toString() ?: ""
                pokeDataListFiltered = if (charString.isEmpty()) pokeDataList else {
                    val filteredList = mutableListOf<Result?>()
                    pokeDataList
                        .filter {
                            (it?.name?.contains(constraint.toString()) == true)
            //                            or (it?.url?.contains(constraint.toString()) == true)

                        }
                        .forEach { filteredList.add(it) }
                    filteredList

                }
                return FilterResults().apply { values = pokeDataListFiltered }
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {

                pokeDataListFiltered = if (results?.values == null)
                    ArrayList()
                else
                    results.values as MutableList<Result?>
                notifyDataSetChanged()
            }
        }
    }
}