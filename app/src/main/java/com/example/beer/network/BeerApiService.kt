package com.example.beer.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

const val BASE_URL = "https://api.punkapi.com/v2/"

val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface BeerApiService{
    @GET(value = "beers")
    suspend fun getBeer() : List<Beer>
}

object BeerApi{
    val retrofitService : BeerApiService by lazy {
        retrofit.create(BeerApiService::class.java)
    }
}