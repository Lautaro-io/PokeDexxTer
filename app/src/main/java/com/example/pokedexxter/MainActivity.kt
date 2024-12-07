package com.example.pokedexxter

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import com.example.pokedexxter.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var retrofit: Retrofit


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        retrofit = getRetrofit()
        initUI()
    }

    private fun initUI() {
        binding.svPokemon.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchPokemon(query.orEmpty().trim().lowercase())
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }

        })
    }

    private fun searchPokemon(name: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = retrofit.create(ApiService::class.java).getPokemon(name)
            if (response.isSuccessful) {
                runOnUiThread {
                    val pokemon: List<PokemonDataResponse?> = listOf(response.body())
                    navigateToResult(pokemon)
                }
            } else {
                when (response.code()) {
                    404 -> runOnUiThread {
                        Log.i("Chelo", "Pokémon no encontrado")
                        Toast.makeText(
                            this@MainActivity,
                            "Pokémon no encontrado",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    else -> Log.i(
                        "Chelo",
                        "Error: ${response.code()} - ${response.errorBody()?.string()}"
                    )
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

    private fun navigateToResult(pokeList: List<PokemonDataResponse?>) {
        val intent = Intent(this@MainActivity, PokomDataActivity::class.java)
        intent.putParcelableArrayListExtra("EXTRA_LIST", ArrayList(pokeList))
        Log.d("Chelo", "Lista enviada: $pokeList")

        startActivity(intent)
    }
}