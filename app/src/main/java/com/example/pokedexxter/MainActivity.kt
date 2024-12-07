package com.example.pokedexxter

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.pokedexxter.databinding.ActivityMainBinding
import com.example.pokedexxter.ui.theme.PokeDexxTerTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class MainActivity : ComponentActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var retrofit: Retrofit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()
        retrofit = getRetrofit()
    }

    private fun initUI() {
        binding.svPokemon.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchPokemon(query.orEmpty())
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
                val pokemon: List<PokemonDataResponse?> = listOf(response.body())
                runOnUiThread {
                    val intent = Intent(this@MainActivity , PokomDataActivity::class.java)
                    intent.putParcelableArrayListExtra(
                        "pokemon_list",
                        ArrayList(pokemon)
                    )
                    startActivity(intent)
                }
            } else {
                Log.i("Chelo", "Error")
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