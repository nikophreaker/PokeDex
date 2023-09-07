package com.nikoprayogaw.pokedex.model.data.pokelist

import com.google.gson.annotations.SerializedName

data class PokeList(
    @SerializedName("count")
    val count: Int?,
    @SerializedName("next")
    val next: String?,
    @SerializedName("previous")
    val previous: String?,
    @SerializedName("results")
    val results: List<Result?>?
)