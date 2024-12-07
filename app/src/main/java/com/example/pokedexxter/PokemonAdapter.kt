package com.example.pokedexxter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class PokemonAdapter(val pokemons: ArrayList<PokemonDataResponse?>):RecyclerView.Adapter<PokemonViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recyclerview,parent , false)
        return PokemonViewHolder(view)
    }

    override fun getItemCount() = pokemons.size

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        pokemons[position]?.let { holder.render(it) }
    }
}