package com.example.foodrecipeapp2.api

import com.example.foodrecipeapp2.BuildConfig
import com.example.foodrecipeapp2.model.Detail
import com.example.foodrecipeapp2.model.RecipeResponse
import com.example.foodrecipeapp2.util.Constant

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {
    @GET("recipes/complexSearch?apiKey=da3197b4102241f8ae2fcc793d2fa878")
    suspend fun getAllRecipe(@Query("offset") number : Int) : RecipeResponse
    @GET("recipes/{id}/information?includeNutrition=true")
    suspend fun getSingleRecipe(
        @Path("id") id:Int,
        @Query("apiKey") apiKey: String = BuildConfig.API_KEY,
    ): Response<Detail>
    @GET("recipes/complexSearch")
    suspend fun getQurryRecipe(
        @Query("apiKey") apiKey: String = BuildConfig.API_KEY,
        @Query("number") number: Int = Constant.PER_PAGE,
        @Query("offset") page: Int,
        @Query("query") query: String,
    ): Response<RecipeResponse>
}


//deep api: 95647aa991af41e89949ac4bcd60942c
//my api zignuts : da3197b4102241f8ae2fcc793d2fa878
//personal api: 764390081c20410e8c2a2c9ffc459114
//fake MAil Api;- 5e6e52beecef465c8e9fe1b0d61e8841
//bhoomi Api : f8e4cf94f97547e6ab279071c4a09ccc
//unkonw api : 9d1751511fdd4ef0b31098f7a87264ec
//uankonwn 2 api : b9852e36d72d414ba21aac67ef9da53c
//uankonwn 3 api :4cd93b1b1eea49b89d849a06fd60a6c7
//mail deep : 210511214016@paruluniversity.ac.in
//fakemail : klaus.ziair@foundtoo.com
//bhoomimail : bhoomik@zignuts.com