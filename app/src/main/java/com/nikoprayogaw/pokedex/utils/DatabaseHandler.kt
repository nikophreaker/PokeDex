package com.nikoprayogaw.pokedex.utils

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.nikoprayogaw.pokedex.model.repo.source.local.PokedexDatabase

abstract class DatabaseHandler : PokedexDatabase() {

    companion object {
        @Volatile private var instance: PokedexDatabase? = null

        fun getDatabase(context: Context): PokedexDatabase =
            instance ?: synchronized(this) { instance ?: buildDatabase(context).also { instance = it } }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(
                appContext,
                PokedexDatabase::class.java, "pokedexdb"
            )
//                .addMigrations(MIGRATION_1_2)
//                .fallbackToDestructiveMigration() pake ini bisa tp rusak database
                .allowMainThreadQueries()
                .build()

        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("DROP TABLE IF EXISTS `userDetail`")
                database.execSQL("CREATE TABLE IF NOT EXISTS `userDetail` (`userId` INTEGER PRIMARY KEY NOT NULL, `userData` TEXT NOT NULL)")
            }
        }
    }

}