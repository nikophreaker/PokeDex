package com.nikoprayogaw.pokedex.model.api

import com.nikoprayogaw.pokedex.model.data.pokedetail.PokeDetail
import com.nikoprayogaw.pokedex.model.data.pokelist.PokeList
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface ApiService {

    @GET("pokemon")
    fun getPokeList(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Observable<PokeList>

    @GET("pokemon/{id}")
    fun getPokeDetail(
        @Path("id") id: String
    ) : Observable<PokeDetail>

    companion object Factory {
        fun create(url: String): ApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(
                    GsonConverterFactory.create()
                )
                .addCallAdapterFactory(
                    RxJava2CallAdapterFactory.create()
                )
                .build()
            return retrofit.create(ApiService::class.java)
        }
    }
}