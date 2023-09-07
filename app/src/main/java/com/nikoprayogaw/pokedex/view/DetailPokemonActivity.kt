package com.nikoprayogaw.pokedex.view

import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.nikoprayogaw.pokedex.databinding.ActivityDetailPokemonBinding
import com.nikoprayogaw.pokedex.utils.PokemonColorUtil
import com.nikoprayogaw.pokedex.utils.SingleLiveEvent
import com.nikoprayogaw.pokedex.utils.obtainViewModel
import com.nikoprayogaw.pokedex.view.adapter.ViewPagerAdapter
import com.nikoprayogaw.pokedex.viewmodel.PokeDetailViewModel

class DetailPokemonActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailPokemonBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailPokemonBinding.inflate(layoutInflater).apply {
            vmPokeDetail = this@DetailPokemonActivity.obtainViewModel(PokeDetailViewModel::class.java)
        }
        binding.lifecycleOwner = this@DetailPokemonActivity
        setContentView(binding.root)

        val pager = binding.viewPager
        val tabs = binding.tabs
        pager.adapter =
            ViewPagerAdapter(supportFragmentManager, baseContext)
        tabs.setupWithViewPager(pager)
    }

    override fun onResume() {
        super.onResume()
        val idPokemon = intent.getStringExtra("idPokemon")
        binding.vmPokeDetail?.start(idPokemon.toString())
    }
}