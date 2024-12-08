package com.example.pokedexxter

import com.google.gson.annotations.SerializedName

data class PokemonListResponse (
    @SerializedName("results") val results: List<PokemonBasicInfo>
)

data class PokemonBasicInfo(
    @SerializedName("name") val name:String,
    @SerializedName("url") val url:String
)