package com.nikoprayogaw.pokedex.model.data.pokedetail


import com.google.gson.annotations.SerializedName

data class AbilityX(
    @SerializedName("name")
    val name: String?,
    @SerializedName("url")
    val url: String?
)