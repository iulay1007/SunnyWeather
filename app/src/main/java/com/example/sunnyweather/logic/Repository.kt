package com.example.sunnyweather.logic
import androidx.lifecycle.liveData
import com.example.sunnyweather.logic.model.Place

//import androidx.lifecycle.liveData
import com.example.sunnyweather.logic.model.PlaceResponse
import com.example.sunnyweather.logic.network.SunnyWeatherNetwork
import kotlinx.coroutines.Dispatchers
import java.lang.Exception
import java.lang.RuntimeException
import kotlin.Result.Companion.failure

object Repository {
    fun seachPlaces(query:String) = liveData (Dispatchers.IO) {
        val result = try {
            val placeResponse=SunnyWeatherNetwork.searchPlaces(query)
            if (placeResponse.status=="ok"){
                val places=placeResponse.places
                Result.success(places)
            }else{
                failure(RuntimeException("response status is ${placeResponse.status}"))
            }
        }catch (e:Exception){
            failure<List<Place>>(e)
        }
        emit(result)
    }
}