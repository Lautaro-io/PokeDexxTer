package com.example.pokedexxter

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class PokemonDataResponse (
    @SerializedName("name") val name:String,
    @SerializedName("types") val types: List<TypeSlot>,
    @SerializedName("sprites") val image: ImageSprite
):Parcelable

@Parcelize
data class TypeSlot(
    @SerializedName("type") val type: TypeInfo
):Parcelable

@Parcelize
data class TypeInfo(
    @SerializedName("name") val name: String
):Parcelable

@Parcelize
data class ImageSprite(
    @SerializedName("front_default") val src:String

):Parcelable