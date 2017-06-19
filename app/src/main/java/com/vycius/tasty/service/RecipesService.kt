package com.vycius.tasty.service

import com.vycius.tasty.model.Recipe
import retrofit2.Call
import retrofit2.http.GET

interface RecipesService {
    @GET("android-baking-app-json")
    fun recipes(): Call<List<Recipe>>
}

