package com.example.androidapp.networks

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL =
    "https://api.unsplash.com/"


private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface unsplashAPI {
    @GET("photos")
    suspend fun getWallpapers(
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 20,
        @Query("client_id") clientId: String = "IojT_6PG4G1kLmK2meO27dFQGz39z9vZyax1zFsAOiY"
    ):List<Wallpapers>
}
//api = function = method
object UnplashApi {
    val retrofitService : unsplashAPI by  lazy {
        retrofit.create(unsplashAPI::class.java)
    }
}