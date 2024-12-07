package com.example.pokedexxter

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET ("pokemon/{name}")
    suspend fun getPokemon(@Path("name") pokemonName:String ) :Response<PokemonDataResponse>

}