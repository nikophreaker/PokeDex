package com.nikoprayogaw.pokedex.model.data.pokedetail


import com.google.gson.annotations.SerializedName

data class Other(
    @SerializedName("dream_world")
    val dreamWorld: DreamWorld?,
    @SerializedName("home")
    val home: Home?,
    @SerializedName("official-artwork")
    val officialArtwork: OfficialArtwork?
)