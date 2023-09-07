package com.nikoprayogaw.pokedex.view

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.nikoprayogaw.pokedex.R
import com.nikoprayogaw.pokedex.databinding.ActivityDetailPokemonBinding
import com.nikoprayogaw.pokedex.utils.obtainViewModel
import com.nikoprayogaw.pokedex.viewmodel.PokeDetailViewModel

class DetailPokemonActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityDetailPokemonBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailPokemonBinding.inflate(layoutInflater).apply {
            vmPokeDetail = this@DetailPokemonActivity.obtainViewModel(PokeDetailViewModel::class.java)
        }
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        val idPokemon = intent.getStringExtra("idPokemon")
        binding.vmPokeDetail?.start(idPokemon.toString())
    }
}