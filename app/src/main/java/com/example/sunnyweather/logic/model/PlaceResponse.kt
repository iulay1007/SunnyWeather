package com.example.sunnyweather.logic.model

import com.google.gson.annotations.SerializedName




    data class PlaceResponse(val status:String, val places:List <Place>)
    data class Place(val name: String,val loctaion:Location,
                     @SerializedName("formatted_address")val address:String)
    //SerializedName  让JSON字段和Kotlin字段建立映射关系
    data class Location (val lng:String,val lat:String)
