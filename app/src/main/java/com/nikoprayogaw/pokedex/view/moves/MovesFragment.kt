package com.nikoprayogaw.pokedex.view.moves

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.nikoprayogaw.pokedex.databinding.FragmentMovesBinding

class MovesFragment : Fragment() {
    private lateinit var viewBinding: FragmentMovesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentMovesBinding.inflate(inflater, container, false)
        viewBinding.lifecycleOwner = viewLifecycleOwner
        return viewBinding.root
    }
}
