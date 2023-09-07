package com.nikoprayogaw.pokedex.view.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.nikoprayogaw.pokedex.databinding.FragmentAboutBinding
import com.nikoprayogaw.pokedex.utils.obtainViewModel
import com.nikoprayogaw.pokedex.view.DetailPokemonActivity
import com.nikoprayogaw.pokedex.viewmodel.PokeDetailViewModel

class AboutFragment : Fragment() {
    private lateinit var viewBinding: FragmentAboutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentAboutBinding.inflate(inflater, container, false).apply {
            vmPokeDetail = (activity as DetailPokemonActivity).obtainViewModel(PokeDetailViewModel::class.java).apply {
                val dataPokemon = this.pokeDataDetail.get()
                textViewDescription.text = "-"
                textViewHeight.text = dataPokemon?.height.toString()
                textViewWeight.text = dataPokemon?.weight.toString()
                textViewGender.text = "-"
                textViewEggCycle.text = "-"
                textViewEggGroups.text = "-"
                textViewBaseEXP.text = dataPokemon?.baseExperience.toString()
            }
        }
        viewBinding.lifecycleOwner = viewLifecycleOwner
        return viewBinding.root
    }

    override fun onResume() {
        super.onResume()
        viewBinding.vmPokeDetail
    }
}
