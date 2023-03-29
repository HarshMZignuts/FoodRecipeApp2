package com.example.foodrecipeapp2.ui.detail

import android.annotation.SuppressLint
import android.app.Application
import android.content.Intent
import android.icu.text.DecimalFormat
import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.foodrecipeapp2.R
import com.example.foodrecipeapp2.model.Detail
import com.example.foodrecipeapp2.model.NutrientX
import com.example.foodrecipeapp2.repository.Repository
import com.example.foodrecipeapp2.util.BaseViewModel
import com.example.foodrecipeapp2.util.NetworkResult
import com.example.foodrecipeapp2.util.PrefManager.getString
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject
import kotlin.properties.Delegates

@HiltViewModel
class DetailViewModel
@Inject
constructor(
    private val repository: Repository,
    application: Application
) : BaseViewModel(application){



    private val _myResponceData = MutableLiveData<NetworkResult<Detail>>()
    val myResponceData: LiveData<NetworkResult<Detail>>
        get() = _myResponceData

    private val mContext = application

    private var vid = -1

    fun getRecipe2(id : Int){
        if (vid == id) {
            return
        } else {
            vid = id
        }
        viewModelScope.launch {

            if (isConnected()) {


                _myResponceData.value = NetworkResult.Loading()
                val response: Response<Detail> = repository.getSingleRecipe(id)
                _myResponceData.value = handleResponse(response)


            } else {
                _myResponceData.value = NetworkResult.Error(
                    context.resources.getString(R.string.no_internet)
                )
            }


        }
    }

    fun carboAmount(nutrients: List<NutrientX>): String {
        lateinit var ca : String
            nutrients.forEach {
                if (it.name  == "Carbohydrates" )
                {
                    ca =  it.amount.toString()
//                    nutrients.let {
//                        it.forEach {
//                            ca = it.amount.toString()
//                            Timber.e("===",ca)
//                        }
//
//
//                    }

                }
            }


        return ca


    }
    fun carboUnite(nutrients: List<NutrientX>): String {
        lateinit var cu : String
        nutrients.forEach {
            if (it.name  == "Carbohydrates" )
            {
                cu = it.unit.toString()


            }
        }


        return cu


    }

    fun carboPer(nutrients: List<NutrientX>): String {
        lateinit var cp : String
        var df = DecimalFormat("#.##")

        nutrients.forEach {
            var p = it.percentOfDailyNeeds

            if (it.name  == "Carbohydrates" )
            {

                nutrients.let {
                    it.forEach {
                        cp = this.mContext.getString(
                                 R.string.percentage_forment,
                                     df.format(p))

                    }


                }

            }
        }

        return cp



    }

    fun fatAmount(nutrients: List<NutrientX>): String {
        lateinit var fa : String
        nutrients.forEach {
            if (it.name  == "Fat" )
            {
                fa = it.amount.toString()
//                nutrients.let {
//                    it.forEach {
//                        fa = it.amount.toString()
//                        Timber.e("===",fa)
//                    }
//
//
//                }

            }
        }


        return fa


    }
    fun fatUnite(nutrients: List<NutrientX>): String {
        lateinit var fu : String
        nutrients.forEach {
            if (it.name  == "Fat" )
            {
                fu = it.unit.toString()
//                nutrients.let {
//                    it.forEach {
//                        fu = it.unit.toString()
//                        Timber.e("===",fu)
//                    }
//
//
//                }

            }
        }


        return fu


    }

    fun fatPer(nutrients: List<NutrientX>): String {
        lateinit var fp : String
        var df = DecimalFormat("#.##")

        nutrients.forEach {
            var p = it.percentOfDailyNeeds

            if (it.name  == "Fat" )
            {

                nutrients.let {
                    it.forEach {
                        fp = this.mContext.getString(
                            R.string.percentage_forment,
                            df.format(p))

                    }


                }

            }
        }

        return fp



    }

    fun proAmount(nutrients: List<NutrientX>): String {
        lateinit var pa : String
        nutrients.forEach {
            if (it.name  == "Protein" )
            {
                pa = it.amount.toString()
//                nutrients.let {
//                    it.forEach {
//                        pa = it.amount.toString()
//                        Timber.e("===",pa)
//                    }
//
//
//                }

            }
        }


        return pa


    }
    fun proUnite(nutrients: List<NutrientX>): String {
        lateinit var pu : String
        nutrients.forEach {
            if (it.name  == "Protein" )
            {
                pu = it.unit.toString()
//                nutrients.let {
//                    it.forEach {
//                        pu = it.unit.toString()
//                        Timber.e("===",pu)
//                    }
//
//
//                }

            }
        }


        return pu


    }

    fun proPer(nutrients: List<NutrientX>): String {
        lateinit var pp : String
        var df = DecimalFormat("#.##")

        nutrients.forEach {
            var p = it.percentOfDailyNeeds

            if (it.name  == "Protein" )
            {

                nutrients.let {
                    it.forEach {
                        pp = this.mContext.getString(
                            R.string.percentage_forment,
                            df.format(p))

                    }


                }

            }
        }

        return pp



    }

    fun calAmount(nutrients: List<NutrientX>): String {
        lateinit var pa : String
        nutrients.forEach {
            if (it.name  == "Calories" )
            {
                pa = it.amount.toString()

//                nutrients.let {
//                    it.forEach {
//                        pa = it.amount.toString()
//                        Timber.e("===",pa)
//                    }
//
//
//                }

            }
        }


        return pa


    }
    fun calUnite(nutrients: List<NutrientX>): String {
        lateinit var pu : String
        nutrients.forEach {
            if (it.name  == "Calories" )
            {
                pu = it.unit.toString()
//                nutrients.let {
//                    it.forEach {
//                        pu = it.unit.toString()
//                        Timber.e("===",pu)
//                    }
//
//
//                }

            }
        }


        return pu


    }
    fun carboPRogress(nutrients: List<NutrientX>): Int {
        var pp by Delegates.notNull<Int>()
        // var df = DecimalFormat("#.##")

        nutrients.forEach {


            if (it.name  == "Carbohydrates" )
            {
                 pp = it.percentOfDailyNeeds?.toInt()!!

//                nutrients.let {
//                    it.forEach {
//                        pp = this.mContext.getString(
//                            R.string.percentage_forment,
//                            df.format(p))
//
//                    }
//
//
//                }

            }
        }

        return pp



    }
    fun fatPRogress(nutrients: List<NutrientX>): Int {
        var pp by Delegates.notNull<Int>()
        // var df = DecimalFormat("#.##")

        nutrients.forEach {


            if (it.name  == "Fat" )
            {
                pp = it.percentOfDailyNeeds?.toInt()!!

//                nutrients.let {
//                    it.forEach {
//                        pp = this.mContext.getString(
//                            R.string.percentage_forment,
//                            df.format(p))
//
//                    }
//
//
//                }

            }
        }

        return pp



    }
    fun proPRogress(nutrients: List<NutrientX>): Int {
        var pp by Delegates.notNull<Int>()
        // var df = DecimalFormat("#.##")

        nutrients.forEach {


            if (it.name  == "Protein" )
            {
                pp = it.percentOfDailyNeeds?.toInt()!!

//                nutrients.let {
//                    it.forEach {
//                        pp = this.mContext.getString(
//                            R.string.percentage_forment,
//                            df.format(p))
//
//                    }
//
//
//                }

            }
        }

        return pp



    }
    fun calPRogress(nutrients: List<NutrientX>): Int {
        var pp by Delegates.notNull<Int>()
        // var df = DecimalFormat("#.##")

        nutrients.forEach {


            if (it.name  == "Calories" )
            {
                pp = it.percentOfDailyNeeds?.toInt()!!

//                nutrients.let {
//                    it.forEach {
//                        pp = this.mContext.getString(
//                            R.string.percentage_forment,
//                            df.format(p))
//
//                    }
//
//
//                }

            }
        }

        return pp



    }

    fun setonClickseeRecipe(detail : Detail){
        val intent = Intent(Intent.ACTION_VIEW)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.data = Uri.parse(detail.sourceUrl)
        mContext.startActivity(intent)

    }

    fun setRatting(detail : Detail) : Float{
        val rating : Float = detail.healthScore!!.toFloat() * 5 / 100
        return rating
    }





}