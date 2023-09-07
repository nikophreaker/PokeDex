package com.nikoprayogaw.pokedex.model.repo.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.nikoprayogaw.pokedex.model.data.pokelist.*
import com.nikoprayogaw.pokedex.utils.TypeConverter

@Database(entities = [Result::class], version = 1, exportSchema = false)

@TypeConverters(TypeConverter::class)
abstract class PokedexDatabase : RoomDatabase() {
    abstract fun pokeListDao(): PokeListDao
}