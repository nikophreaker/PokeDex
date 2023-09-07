package com.nikoprayogaw.pokedex.model.data.pokelist

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "pokeList")
data class Result(
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0,

    @ColumnInfo(name = "name")
    @SerializedName("name")
    val name: String?,

    @ColumnInfo(name = "url")
    @SerializedName("url")
    val url: String?
)