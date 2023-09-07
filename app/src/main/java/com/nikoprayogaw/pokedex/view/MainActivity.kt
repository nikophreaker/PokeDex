package com.nikoprayogaw.pokedex.view

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.leinardi.android.speeddial.SpeedDialView
import com.nikoprayogaw.pokedex.R
import com.nikoprayogaw.pokedex.databinding.ActivityMainBinding
import com.nikoprayogaw.pokedex.model.repo.source.local.PokedexDatabase
import com.nikoprayogaw.pokedex.utils.ConstantVariable
import com.nikoprayogaw.pokedex.utils.DatabaseHandler
import com.nikoprayogaw.pokedex.utils.obtainViewModel
import com.nikoprayogaw.pokedex.view.adapter.PokeListAdapter
import com.nikoprayogaw.pokedex.viewmodel.PokeListViewModel
import java.time.Duration

class MainActivity : AppCompatActivity(), LoadDataListener, SearchFragment.OnInputListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var pokeListAdapter: PokeListAdapter
    private var currentOffset = ConstantVariable.offset + ConstantVariable.limit
    private var limit = ConstantVariable.limit
    private lateinit var pokedexdb: PokedexDatabase

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
            .apply {
                vmPokeList = this@MainActivity.obtainViewModel(PokeListViewModel::class.java).apply {
                    openPokemon.observe(this@MainActivity) {
                        Log.i("idPokemon ", it.toString())
                        openPokemon(it)
                    }
                    onLoadDataListener = this@MainActivity
                }
            }
        val view = binding.root
        setContentView(view)
        initializeRecycler()
        setupRv()
        pokedexdb = DatabaseHandler.getDatabase(baseContext)
        binding.swipeRefresh.setOnRefreshListener {
            currentOffset = 0
            binding.vmPokeList?.start()
        }

        binding.content.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            val pb = binding.progressbar
            if (v.getChildAt(v.childCount - 1) != null) {
                if ((scrollY >= (v.getChildAt(v.childCount - 1).measuredHeight - v.measuredHeight)) &&
                    scrollY > oldScrollY
                ) {
//                    pb.visibility = View.VISIBLE
                    binding.vmPokeList?.resume(currentOffset,ConstantVariable.limit)
                    currentOffset += limit
                }
                if (scrollY < (v.getChildAt(v.childCount - 1).measuredHeight - v.measuredHeight) &&
                    scrollY <= oldScrollY
                ) {
                    pb.visibility = View.INVISIBLE
                }
            }

        })

        val speedDialView = binding.speedDial
        speedDialView.inflate(R.menu.menu_pokedex)
        speedDialView.setOnActionSelectedListener(SpeedDialView.OnActionSelectedListener { actionItem ->
            when (actionItem.id) {
                R.id.sortAsc -> {
                    sort(true)
                    speedDialView.close()
                    return@OnActionSelectedListener true
                }
                R.id.sortAscNumber -> {
                    sortNumber(true)
                    speedDialView.close()
                    return@OnActionSelectedListener true
                }
                R.id.sortDescNumber -> {
                    sortNumber(false)
                    speedDialView.close()
                    return@OnActionSelectedListener true
                }
                R.id.menuSearch -> {
                    showSearch()
                    speedDialView.close()
                    return@OnActionSelectedListener true
                }
                else -> {
                    sort(false)
                    speedDialView.close()
                    return@OnActionSelectedListener true
                }
            }
        })

        binding.vmPokeList?.start()
    }

    private fun sort(asc: Boolean) {
        if (asc) {
            pokeListAdapter.pokeDataListFiltered.sortBy { it?.name }
            pokeListAdapter.notifyDataSetChanged()
        }
        else {
            pokeListAdapter.pokeDataListFiltered.sortByDescending { it?.name }
            pokeListAdapter.notifyDataSetChanged()
        }
    }
    private fun sortNumber(asc: Boolean) {
        if (asc) {
            pokeListAdapter.pokeDataListFiltered.sortBy { it?.url }
            pokeListAdapter.notifyDataSetChanged()
        }
        else {
            pokeListAdapter.pokeDataListFiltered.sortByDescending { it?.url }
            pokeListAdapter.notifyDataSetChanged()
        }
    }

    override fun sendInput(input: String?) {
        pokeListAdapter.filter.filter(input)
    }
    private fun showSearch() {
        val dialog = SearchFragment()
        dialog.show(supportFragmentManager, "")
    }

    private fun openPokemon(id: String) {
        val i = Intent(this, DetailPokemonActivity::class.java)
            .putExtra("idPokemon", id)
        startActivity(i)
    }

    private fun setupRv() {
        val viewModel = binding.vmPokeList
        if (viewModel != null) {
            binding.rvPokeList.visibility = View.VISIBLE
            pokeListAdapter = PokeListAdapter(viewModel.pokeDataList, viewModel)
            binding.rvPokeList.adapter = pokeListAdapter
        }

    }

    private fun initializeRecycler() {
        val gridLayout = GridLayoutManager(baseContext, 2)
        binding.rvPokeList.apply {
            setHasFixedSize(true)
            layoutManager = gridLayout
        }
    }

    override fun onDataLoad() {
        binding.onLoad.visibility = View.GONE
        binding.noData.visibility = View.GONE
        binding.tryAgain.visibility = View.GONE
        binding.swipeRefresh.isRefreshing = false
    }

    override fun onNotAvailable() {
        binding.onLoad.visibility = View.GONE
        binding.noData.visibility = View.VISIBLE
        binding.tryAgain.visibility = View.GONE
        binding.swipeRefresh.isRefreshing = false
    }

    override fun onError(msg: String?) {
        binding.onLoad.visibility = View.GONE
        binding.noData.visibility = View.GONE
        binding.tryAgain.visibility = View.VISIBLE
        binding.swipeRefresh.isRefreshing = false
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show()
    }

    override fun onDataResumeLoad() {

    }

    override fun onNotAvailableResume() {

    }

    override fun onErrorResume(msg: String?) {

    }
}