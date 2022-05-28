package com.example.beer.network

import com.squareup.moshi.Json

data class Beer (
    val name: String,
    val tagline: String,
    val first_brewed: String,
    val description: String,

)