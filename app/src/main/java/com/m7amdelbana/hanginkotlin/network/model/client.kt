package com.m7amdelbana.hanginkotlin.network.model

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class client {
    companion object{
        val retrofit:Retrofit?=Retrofit.Builder()
            .baseUrl("https://node-hangin.herokuapp.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}