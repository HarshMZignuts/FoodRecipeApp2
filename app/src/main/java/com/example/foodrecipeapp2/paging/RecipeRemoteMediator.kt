package com.example.foodrecipeapp2.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.example.foodrecipeapp2.api.ApiInterface
import com.example.foodrecipeapp2.model.Recipe
import com.example.foodrecipeapp2.model.RecipeRemoteKeys
import com.example.foodrecipeapp2.model.RecipeResponse

//@ExperimentalPagingApi
//class RecipeRemoteMediator(
//    private val api : ApiInterface
//) : RemoteMediator<Int,Recipe>() {
//    override suspend fun load(loadType: LoadType, state: PagingState<Int, RecipeResponse>): MediatorResult {
//        return try {
//            val currentPage =
//
//        } catch (e: Exception) {
//            MediatorResult.Error(e)
//
//        }
//    }
////    private suspend fun getRemoteKeyForLastItem(
////        state: PagingState<Int, RecipeResponse>
////    ) : RecipeRemoteKeys{
////        return state.pages.firstOrNull{it.data.isNotEmpty()}?.data?.lastOrNull()
////            ?.number
////    }
//}
