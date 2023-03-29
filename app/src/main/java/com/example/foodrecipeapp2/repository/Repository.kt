package com.example.foodrecipeapp2.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.foodrecipeapp2.api.ApiInterface
import com.example.foodrecipeapp2.model.Detail
import com.example.foodrecipeapp2.model.RecipeResponse
import com.example.foodrecipeapp2.paging.RecipePagingSource

import retrofit2.Response
import retrofit2.http.Query
import javax.inject.Inject

class Repository @Inject constructor(
    private val api : ApiInterface
) {
//    var getAllRecipe = Pager(
//        config = PagingConfig(pageSize = 10, maxSize = 100),
//        pagingSourceFactory = {
//            RecipePagingSource(api)
//        }
//    ).liveData

//    fun getQurryRecipe(query: String) {
//        getAllRecipe = Pager(
//            config = PagingConfig(pageSize = 10, maxSize = 100),
//            pagingSourceFactory = {
//                RecipeQurryPagingSource(api,query)
//            }
//        ).liveData
//    }

    suspend fun getSingleRecipe(id:Int): Response<Detail> {
        return api.getSingleRecipe(id)
    }
    suspend fun getQurryRecipe(page:Int,query: String): Response<RecipeResponse>{
        return api.getQurryRecipe(page = page, query = query)
    }


}