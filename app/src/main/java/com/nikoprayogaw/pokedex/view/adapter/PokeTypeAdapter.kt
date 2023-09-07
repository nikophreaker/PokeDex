package com.nikoprayogaw.pokedex.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.nikoprayogaw.pokedex.R
import com.nikoprayogaw.pokedex.databinding.ItemTypeBinding
import com.nikoprayogaw.pokedex.model.data.pokedetail.Type

class PokeTypeAdapter(
    private var pokeTypeList: MutableList<Type?>
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val pokeItemBinding: ItemTypeBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.item_type,
            parent,false
        )

        return PokeHolder(pokeItemBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = pokeTypeList[position]
        (holder as PokeHolder).bindRows(data)
    }

    override fun getItemCount(): Int = pokeTypeList.size

    fun replaceData(pokeType: MutableList<Type?>) {
        setList(pokeType)
    }

    private fun setList(pokeType: MutableList<Type?>) {
        this.pokeTypeList = pokeType
        notifyDataSetChanged()
    }

    class PokeHolder(binding: ItemTypeBinding) : RecyclerView.ViewHolder(binding.root) {
        private val itemItemBinding = binding

        fun bindRows(pokeType: Type?) {
            itemItemBinding.data = pokeType
            itemItemBinding.executePendingBindings()
        }
    }
}