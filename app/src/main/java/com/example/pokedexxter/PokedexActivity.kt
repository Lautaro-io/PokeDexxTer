package com.example.pokedexxter

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokedexxter.databinding.ActivityPokedexBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PokedexActivity : AppCompatActivity() {

    private lateinit var binding : ActivityPokedexBinding
    private lateinit var retrofit : Retrofit
    private lateinit var adapter  :PokemonAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pokedex)
        binding = ActivityPokedexBinding.inflate(layoutInflater)
        setContentView(binding.root)
        retrofit = getRetrofit()
        initData()
        initUI()
    }

    private fun initUI() {
        adapter = PokemonAdapter(emptyList())
        binding.rvPokedex.adapter = adapter
        binding.rvPokedex.layoutManager = LinearLayoutManager(this)
    }

    private fun initData(){
        binding.progresBar.isVisible = true
        CoroutineScope(Dispatchers.IO).launch {
            val response = retrofit.create(ApiService::class.java).getPokedex()
            if (response.isSuccessful){
                val pokemonResults = response.body()?.results.orEmpty()
                val pokemonDetail = mutableListOf<PokemonDataResponse>()
                pokemonResults.forEach{ result ->
                    val pokemonResponse = retrofit.create(ApiService::class.java).getPokemon(result.name)
                    if (pokemonResponse.isSuccessful){
                        pokemonResponse.body()?.let { pokemonDetail.add(it) }
                        runOnUiThread { binding.progresBar.isVisible = false
                                        adapter.updateList(pokemonDetail)
                        }
                    }
                }
            }
        }
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}