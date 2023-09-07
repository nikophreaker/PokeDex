package com.nikoprayogaw.pokedex.model.data.pokedetail


import com.google.gson.annotations.SerializedName

data class GenerationV(
    @SerializedName("black-white")
    val blackWhite: BlackWhite?
)