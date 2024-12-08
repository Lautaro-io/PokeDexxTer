package com.example.pokedexxter

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET ("pokemon/{name}")
    suspend fun getPokemon(@Path("name") pokemonName:String ) :Response<PokemonDataResponse>

    @GET ("pokemon")

    suspend fun getPokedex(
        @Query("limit") limit: Int = 100000,
        @Query("offset") offset: Int = 0
    ) :Response<PokemonListResponse>

}