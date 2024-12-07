package com.example.pokedexxter

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedexxter.databinding.ActivityPokomDataBinding

class PokomDataActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPokomDataBinding
    private lateinit var adapter : PokemonAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityPokomDataBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()
    }


    private fun initUI() {
        val pokeList :ArrayList<PokemonDataResponse?> = intent.getParcelableArrayListExtra("EXTRA_LIST")!!
        adapter = PokemonAdapter(pokeList)
        binding.rvPokemon.setHasFixedSize(true)
        binding.rvPokemon.layoutManager = LinearLayoutManager(this)
        binding.rvPokemon.adapter = adapter
    }
}