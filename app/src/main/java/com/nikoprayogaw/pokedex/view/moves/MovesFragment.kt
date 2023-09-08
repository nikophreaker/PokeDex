package com.nikoprayogaw.pokedex.view.moves

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.nikoprayogaw.pokedex.databinding.FragmentMovesBinding
import com.nikoprayogaw.pokedex.utils.obtainViewModel
import com.nikoprayogaw.pokedex.view.DetailPokemonActivity
import com.nikoprayogaw.pokedex.viewmodel.PokeDetailViewModel

class MovesFragment : Fragment() {
    private lateinit var viewBinding: FragmentMovesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentMovesBinding.inflate(inflater, container, false).apply {
            vmPokeDetail = (activity as DetailPokemonActivity).obtainViewModel(PokeDetailViewModel::class.java)
        }
        viewBinding.lifecycleOwner = viewLifecycleOwner
        return viewBinding.root
    }
}
