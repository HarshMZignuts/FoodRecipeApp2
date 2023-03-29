package com.example.foodrecipeapp2.ui.overview

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.foodrecipeapp2.model.Recipe
import com.example.foodrecipeapp2.paging.RecipePagingSource
import com.example.foodrecipeapp2.repository.Repository
import com.example.foodrecipeapp2.util.BaseViewModel
import com.example.foodrecipeapp2.util.Constant
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OverViewModel
@Inject
constructor(
    val repository : Repository,
    application: Application
) : BaseViewModel(application){
    private var query:String =""
    var recipe = Pager(
        config = PagingConfig(pageSize = Constant.PER_PAGE, prefetchDistance = 2),
        pagingSourceFactory = {
            RecipePagingSource(repository, query = query)
        }
    ).flow.cachedIn(viewModelScope)

    fun searchRecipe(query:String){
        this.query = query
    }

//    lateinit var list : LiveData<PagingData<Recipe>>
//    init {
//        getAllRecipe()
//
//    }

   // val list = repository.getAllRecipe().cachedIn(viewModelScope)
//    fun getAllRecipe(){
//       list = repository.getAllRecipe.cachedIn(viewModelScope)
//    }
//    fun getQurryRecipe2(query: String){
//        repository.getQurryRecipe(query)
//    }

}