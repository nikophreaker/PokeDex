package com.nikoprayogaw.pokedex.model.data.pokedetail


import com.google.gson.annotations.SerializedName

data class VersionDetail(
    @SerializedName("rarity")
    val rarity: Int?,
    @SerializedName("version")
    val version: Version?
)