package com.m7amdelbana.hanginkotlin.network.model

import com.google.gson.annotations.SerializedName

data class user(

    @SerializedName("firstName")
    var firstName : String?=null,
    @SerializedName("lastName")
    var lastName : String?=null,
    @SerializedName("email")
    var email : String?=null,
    @SerializedName("phone")
    var phone : Int?=0,
    @SerializedName("password")
    var password : Int?=0

)
