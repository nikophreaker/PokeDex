package com.nikoprayogaw.pokedex.model.data.pokelist

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PokeListDao {
    @Insert
    fun addData(pokeList: MutableList<Result?>)

    @Query("select * from pokeList")
    fun getPokeList(): MutableList<Result?>?

    @Query("DELETE FROM pokeList")
    fun deletePokelist()
}