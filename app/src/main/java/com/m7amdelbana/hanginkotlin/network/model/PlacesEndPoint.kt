package com.m7amdelbana.hanginkotlin.network.model

import com.google.gson.JsonObject
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface PlacesEndPoint {
    @GET("https://node-hangin.herokuapp.com/api/places")
    fun getplaces() : Call<List<Place>>
    @POST("https://node-hangin.herokuapp.com/api/users/login")
    fun login(
        @Body body: JsonObject
    ) : Call<token>

    @GET("https://node-hangin.herokuapp.com/api/users/me")
    fun getAccount(
        //application/json
        @Header("Content-Type")Content:String,
        @Header("x-auth-token")token:String
    ):Call<Account>

    @POST("https://node-hangin.herokuapp.com/api/users/register")
    fun register(
        @Body body: JsonObject
    ) : Call<token>
}