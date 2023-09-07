package com.nikoprayogaw.pokedex.model.data.pokedetail


import com.google.gson.annotations.SerializedName

data class GenerationIi(
    @SerializedName("crystal")
    val crystal: Crystal?,
    @SerializedName("gold")
    val gold: Gold?,
    @SerializedName("silver")
    val silver: Silver?
)