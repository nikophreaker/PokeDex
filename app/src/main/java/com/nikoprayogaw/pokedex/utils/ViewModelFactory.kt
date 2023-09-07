package com.nikoprayogaw.pokedex.utils

import android.annotation.SuppressLint
import android.app.Application
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nikoprayogaw.pokedex.model.repo.DataRepo
import com.nikoprayogaw.pokedex.viewmodel.PokeDetailViewModel
import com.nikoprayogaw.pokedex.viewmodel.PokeListViewModel

class ViewModelFactory private constructor(
    private val application: Application,
    private val dataRepo: DataRepo
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>) = with(modelClass) {
        when {
            isAssignableFrom(PokeListViewModel::class.java) ->
                PokeListViewModel(application, dataRepo)
            isAssignableFrom(PokeDetailViewModel::class.java) ->
                PokeDetailViewModel(application, dataRepo)
            else ->
                throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    } as T

    companion object {
        @SuppressLint("StaticFieldLeak")
        @Volatile
        private var INSTANCE: ViewModelFactory? = null
        fun getInstance(application: Application) =
            INSTANCE
                ?: synchronized(ViewModelFactory::class.java) {
                    INSTANCE
                        ?: ViewModelFactory(
                            application,
                            Injection.providedMainDataMatch(application.applicationContext, DatabaseHandler.getDatabase(application.applicationContext))
                        )
                            .also { INSTANCE = it }
                }

        @VisibleForTesting
        fun destroyInstance() {
            INSTANCE = null
        }
    }
}