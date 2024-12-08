package com.example.pokedexxter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.compose.ui.text.capitalize
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class PokemonViewHolder(val view: View):RecyclerView.ViewHolder(view) {
    private val background:CardView = view.findViewById(R.id.cardContainer)
    private val name:TextView  = view.findViewById(R.id.tvNamePokemon)
    private val picture:ImageView  = view.findViewById(R.id.imgPokemon)

    fun render(pokemon: PokemonDataResponse) {
        name.text = (pokemon.name).capitalize()
        Picasso.get().load(pokemon.image.src).into(picture)
    }
}