package com.nikoprayogaw.pokedex.utils

import android.content.Context
import android.preference.PreferenceManager
import com.nikoprayogaw.pokedex.model.repo.DataRepo
import com.nikoprayogaw.pokedex.model.repo.source.local.DataLocalSource
import com.nikoprayogaw.pokedex.model.repo.source.local.PokedexDatabase
import com.nikoprayogaw.pokedex.model.repo.source.remote.DataRemoteSource

object Injection {
    fun providedMainDataMatch(context: Context, localDB: PokedexDatabase) = DataRepo.getInstance(
        DataRemoteSource.getInstance(localDB)!!,
        DataLocalSource.getInstance(PreferenceManager.getDefaultSharedPreferences(context), localDB)!!)
}