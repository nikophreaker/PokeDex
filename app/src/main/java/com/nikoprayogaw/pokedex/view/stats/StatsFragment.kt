package com.nikoprayogaw.pokedex.view.stats

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.nikoprayogaw.pokedex.databinding.FragmentStatsBinding
import com.nikoprayogaw.pokedex.utils.obtainViewModel
import com.nikoprayogaw.pokedex.view.DetailPokemonActivity
import com.nikoprayogaw.pokedex.viewmodel.PokeDetailViewModel

class StatsFragment : Fragment() {

    private lateinit var viewBinding: FragmentStatsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentStatsBinding.inflate(inflater, container, false).apply {
            vmPokeDetail = (activity as DetailPokemonActivity).obtainViewModel(PokeDetailViewModel::class.java)
        }
        viewBinding.lifecycleOwner = viewLifecycleOwner
        return viewBinding.root
    }

}
