package com.example.sunnyweather.logic.network

import com.example.sunnyweather.logic.model.ServiceCreator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.RuntimeException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

object SunnyWeatherNetwork {
    private val placeService=ServiceCreator.create<PlaceService>()

    suspend fun searchPlaces(query:String)= placeService.searchPlaces(query).await()

    private suspend fun <T> Call<T>.await():T{//定义了一个await函数
        return suspendCoroutine { continuation ->
            enqueue(object : Callback<T>{
            override fun onResponse(call: Call<T>, response: Response<T>) {
              val body=response.body();
                if(body !=null) continuation.resume(body)
                else continuation.resumeWithException(RuntimeException("response body is wrong"))

            }
            override fun onFailure(call: Call<T>, t: Throwable) {
                continuation.resumeWithException(t)
            }

        })  } }
    }
