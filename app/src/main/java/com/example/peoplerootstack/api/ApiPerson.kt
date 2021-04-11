package com.example.peoplerootstack.api

import com.example.peoplerootstack.model.Person
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiPerson {

    @GET("api")
    fun getPerson(@Query("page") page:Int,
                  @Query("results") result:Int = 20,
                  @Query("seed") seed:String = "abc") : Call<Person>

}