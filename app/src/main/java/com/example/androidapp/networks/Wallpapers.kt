package com.example.androidapp.networks

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Wallpapers(
    val id: String,
    @Json(name = "urls") val urls: Urls
)

@JsonClass(generateAdapter = true)
data class Urls(
    val full: String
)
