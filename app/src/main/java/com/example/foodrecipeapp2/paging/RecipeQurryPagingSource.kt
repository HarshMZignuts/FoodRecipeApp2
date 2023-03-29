//package com.example.foodrecipeapp2.paging
//
//import androidx.paging.PagingSource
//import androidx.paging.PagingState
//import com.example.foodrecipeapp2.api.ApiInterface
//import com.example.foodrecipeapp2.model.Recipe
//
//class RecipeQurryPagingSource(private val api : ApiInterface, var Qurry:String) : PagingSource<Int, Recipe>()  {
//    override fun getRefreshKey(state: PagingState<Int, Recipe>): Int? {
//        return state.anchorPosition?.let {
//            state.closestPageToPosition(it)?.prevKey?.plus(1)
//                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
//        }
//    }
//
//    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Recipe> {
//        return try {
//
//            val position = params.key ?: RecipeQurryPagingSource.FIRST_PAGE_NUMBER
//            val qurry = ""
//           // val response = api.getAllRecipe(position)
//
//            val response= api.getQurryRecipe(position,Qurry)
//            LoadResult.Page(
//                data = response.results?.filterNotNull()!!,
//                prevKey = if (position == 1) null else position - 10,
//                nextKey = if (position == response.totalResults) null else position + 10
//
//
////                prevKey = if (position > FIRST_PAGE_NUMBER) position - 1 else null,
////                nextKey = if (position * PAGE_SIZE < response.totalResults!!) position + 1 else null
//            )
////            LoadResult.Page(
////                data = response1.results?.filterNotNull()!!,
////                prevKey = if (position == 1) null else position - 10,
////                nextKey = if (position == response.totalResults) null else position + 10
////            )
//
//
//        }catch (e:Exception){
//            LoadResult.Error(e)
//        }
//    }
//    companion object{
//        const val FIRST_PAGE_NUMBER = 1
//        const val PAGE_SIZE = 20
//    }
//}