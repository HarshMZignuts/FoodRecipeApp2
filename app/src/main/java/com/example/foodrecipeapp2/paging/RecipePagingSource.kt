package com.example.foodrecipeapp2.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.foodrecipeapp2.api.ApiInterface
import com.example.foodrecipeapp2.model.Recipe
import com.example.foodrecipeapp2.repository.Repository
import retrofit2.http.Query
import timber.log.Timber

class RecipePagingSource(
    private val repository: Repository,
    private val query: String
) : PagingSource<Int,Recipe>() {
    override fun getRefreshKey(state: PagingState<Int, Recipe>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Recipe> {
        return try {



            var position = params.key ?: FIRST_PAGE_NUMBER
            //val qurry = ""
            var nextKey = params.key?.plus(10)

            val response = repository.getQurryRecipe(position,query)
            //val response1= api.getQurryRecipe(position,qurry)
            when(params){
                is LoadParams.Refresh -> {
                   position = 0
                    nextKey = 1
                }
                else -> Unit
            }
            LoadResult.Page(
                data = response.body()?.results?.filterNotNull()!!,
                prevKey = null,
                nextKey = if (position == response.body()?.totalResults) null else nextKey


//                prevKey = if (position > FIRST_PAGE_NUMBER) position - 1 else null,
//                nextKey = if (position * PAGE_SIZE < response.totalResults!!) position + 1 else null
            )
//            LoadResult.Page(
//                data = response1.results?.filterNotNull()!!,
//                prevKey = if (position == 1) null else position - 10,
//                nextKey = if (position == response.totalResults) null else position + 10
//            )


        }catch (e:Exception){
            LoadResult.Error(e)
        }
    }
    companion object{
        const val FIRST_PAGE_NUMBER = 0
        const val PAGE_SIZE = 20
    }
}