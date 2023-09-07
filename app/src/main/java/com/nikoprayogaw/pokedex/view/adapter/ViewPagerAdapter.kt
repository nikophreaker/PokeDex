package com.nikoprayogaw.pokedex.view.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.nikoprayogaw.pokedex.R
import com.nikoprayogaw.pokedex.view.about.AboutFragment
import com.nikoprayogaw.pokedex.view.moves.MovesFragment
import com.nikoprayogaw.pokedex.view.stats.StatsFragment

class ViewPagerAdapter(
    supportFragmentManager: FragmentManager,
    context: Context
) : FragmentStatePagerAdapter(supportFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    data class Page(val title: String, val ctor: () -> Fragment)

    @Suppress("MoveLambdaOutsideParentheses")
    private val pages = listOf(
        Page(
            context.getString(R.string.dashboard_tab_1),
            { AboutFragment() }
        ),
        Page(
            context.getString(R.string.dashboard_tab_2),
            { StatsFragment() }
        ),
//        Page(
//            context.getString(R.string.dashboard_tab_3),
//            { EvolutionFragment.newInstance(pokemonId) }
//        ),
        Page(
            context.getString(R.string.dashboard_tab_4),
            { MovesFragment() }
        )
    )

    override fun getItem(position: Int): Fragment {
        return pages[position].ctor()
    }

    override fun getCount(): Int {
        return pages.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return pages[position].title
    }
}
