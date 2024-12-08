package com.example.pokedexxter

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedexxter.databinding.ActivityPokomDataBinding
import com.squareup.picasso.Picasso

class PokomDataActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPokomDataBinding
    private lateinit var adapter: PokemonAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityPokomDataBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()
    }


    private fun initUI() {
        val pokemon: PokemonDataResponse? = intent.getParcelableExtra("EXTRA_LIST")
        if (pokemon != null) {
            binding.pokeName.text = pokemon.name
            Picasso.get().load(pokemon.image.src).into(binding.imgPokemon2)
        }
    }
}